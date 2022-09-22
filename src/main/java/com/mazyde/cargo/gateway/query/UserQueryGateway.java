package com.mazyde.cargo.gateway.query;

import com.mazyde.cargo.model.User;

public interface UserQueryGateway {

    User findByUsername(String username);

}
