package com.mazyde.cargo.gateway.transaction.command;

import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.repository.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTransactionCommandGateway implements TransactionCommandGateway {

    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    public void save(Transaction transaction) {
        transactionJpaRepository.save(transaction);
    }
}

