package com.mazyde.cargo.gateway.user.query;

import com.mazyde.cargo.model.user.User;
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

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User Id is not found"));
    }
}
