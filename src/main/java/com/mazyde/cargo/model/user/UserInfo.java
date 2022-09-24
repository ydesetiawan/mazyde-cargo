package com.mazyde.cargo.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserInfo implements BaseUser, Serializable {

    private Long id;

    private String username;

    private UserInfo(User user) {
        id = user.getId();
        username = user.getUsername();
    }

    public static UserInfo valueOf(User user) {
        return new UserInfo(user);
    }

}
