package com.mazyde.cargo.model;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUtil {

    private static SecurityContextImpl getContext() {

        return (SecurityContextImpl) SecurityContextHolder.getContext();
    }

    private static Optional<Object> getPrincipal() {

        if (null != getContext().getAuthentication()) {
            return Optional.of(getContext().getAuthentication().getPrincipal());
        }

        return Optional.empty();
    }

    private static Optional<BaseUser> getBaseUser() {

        Optional<Object> principal = getPrincipal();

        if (principal.isPresent()) {
            Object o = principal.get();
            if (o instanceof UserDetails) {
                return Optional.of(((UserDetails) o).getUser());
            }
        }

        return Optional.empty();
    }

    public static UserInfo getUserInfo() {

        Optional<BaseUser> baseUser = getBaseUser();

        if (baseUser.isPresent()) {
            BaseUser user = baseUser.get();
            if (user instanceof UserInfo) {
                return (UserInfo) user;
            }
        }

        return null;
    }

    public static String getUsername() {
        Optional<BaseUser> baseUser = getBaseUser();
        return baseUser.isPresent()
            ? baseUser.get().getUsername()
            : "";
    }

    public static Long getUserId() {

        Optional<BaseUser> baseUser = getBaseUser();
        return baseUser.isPresent()
            ? ((UserInfo) baseUser.get()).getId()
            : 0L;
    }


}