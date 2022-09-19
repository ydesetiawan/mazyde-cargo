package com.mazyde.cargo.persistence;

import com.mazyde.cargo.common.util.PresentUtil;
import com.mazyde.cargo.common.vo.AppConstant;
import com.mazyde.cargo.common.vo.SessionDto;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@NoArgsConstructor
public class SecurityUtils {

    public static String getCurrentIp() {

        if (PresentUtil.isPresent(RequestContextHolder.getRequestAttributes())) {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            return PresentUtil.isPresent(ipAddress) ? ipAddress : request.getRemoteAddr();
        } else {
            return "";
        }
    }

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (PresentUtil.isValidClass(authentication.getDetails(), SessionDto.class)) {
                userName = ((SessionDto) authentication.getDetails()).getUserId().getIdentifier();
                if (userName.equals(AppConstant.DEFAULT_USER)) {
                    userName = "0";
                }
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return (userName != null ? Optional.of(userName) : Optional.of(AppConstant.DEFAULT_USER));
    }

    public static SessionDto getCurrentSessionData() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        SessionDto sessionData = (PresentUtil.isPresent(authentication) && (PresentUtil.isValidClass(authentication.getDetails(), SessionDto.class)))
            ? (SessionDto) authentication.getDetails()
            : null;

        return (sessionData != null ? sessionData : SessionDto.guessSession(getCurrentIp()));
    }

    public static boolean isCurrentUserHavePermission(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }
}
