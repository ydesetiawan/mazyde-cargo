package com.mazyde.cargo.gateway.query;

import com.mazyde.cargo.model.User;
import com.mazyde.cargo.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserQueryGateway implements UserQueryGateway {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Username is not found"));
    }
}
