package com.mazyde.cargo.config;

import com.mazyde.cargo.gateway.query.UserQueryGateway;
import com.mazyde.cargo.model.User;
import com.mazyde.cargo.model.UserDetails;
import com.mazyde.cargo.model.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MainAuthenticationProvider implements AuthenticationProvider {

    private final UserQueryGateway userQueryGateway;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userQueryGateway.findByUsername(username);

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        Set<GrantedAuthority> authorities = buildUserAuthority();
        return new UsernamePasswordAuthenticationToken(buildUserForAuthentication(user, authorities), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


    private UserDetails buildUserForAuthentication(User user,
                                                   Set<GrantedAuthority> authorities) {
        return new UserDetails(UserInfo.valueOf(user), user.getUsername(), user.getPassword(), authorities,
            !user.isDeleted());
    }

    private Set<GrantedAuthority> buildUserAuthority() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
