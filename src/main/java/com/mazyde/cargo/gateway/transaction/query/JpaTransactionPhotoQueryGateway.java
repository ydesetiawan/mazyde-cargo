package com.mazyde.cargo.gateway.transaction.query;

import com.mazyde.cargo.model.transaction.TransactionPhoto;
import com.mazyde.cargo.repository.TransactionPhotoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTransactionPhotoQueryGateway implements TransactionPhotoQueryGateway {

    private final TransactionPhotoJpaRepository transactionPhotoJpaRepository;

    @Override
    public TransactionPhoto findFirstByTransactionId(Long transactionId) {
        return transactionPhotoJpaRepository
            .findFirstByTransactionIdOrderByCreatedDateDesc(transactionId)
            .orElse(null);
    }
}
