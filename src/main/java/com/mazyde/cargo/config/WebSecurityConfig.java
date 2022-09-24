package com.mazyde.cargo.config;

import com.mazyde.cargo.usecase.user.AuthenticationUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationUserUseCase mainAuthenticationProvider;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(mainAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((requests) -> {
                    try {
                        requests
                            .antMatchers("/assets/**").permitAll()
                            .and()
                            .authorizeHttpRequests((req) -> req
                                .antMatchers("/main", "/main/**").hasRole("ADMIN"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .failureUrl("/login?error=failure")
                .permitAll()
            )
            .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());

        return http.build();

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
