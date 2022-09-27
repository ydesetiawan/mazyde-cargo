package com.mazyde.cargo.gateway.transaction.query;

import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.repository.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTransactionQueryGateway implements TransactionQueryGateway {

    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    public Transaction findById(Long id) {
        return transactionJpaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("TransactionId is not found"));
    }

    @Override
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionJpaRepository.findAll(pageable);
    }

    @Override
    public Page<Transaction> findAllByReceiptNumber(String receiptNumber, Pageable pageable) {
        return transactionJpaRepository.findAllByReceiptNumber(receiptNumber, pageable);
    }
}
