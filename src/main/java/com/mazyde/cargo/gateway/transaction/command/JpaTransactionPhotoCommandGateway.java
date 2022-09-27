package com.mazyde.cargo.gateway.transaction.command;

import com.mazyde.cargo.model.transaction.TransactionPhoto;
import com.mazyde.cargo.repository.TransactionPhotoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTransactionPhotoCommandGateway implements TransactionPhotoCommandGateway {

    private final TransactionPhotoJpaRepository transactionPhotoJpaRepository;

    @Override
    public void save(TransactionPhoto transactionPhoto) {
        transactionPhotoJpaRepository.save(transactionPhoto);
    }
}
