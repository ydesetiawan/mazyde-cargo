package com.mazyde.cargo.gateway.user.query;

import com.mazyde.cargo.model.user.User;

public interface UserQueryGateway {

    User findByUsername(String username);

    User findById(Long id);

}
