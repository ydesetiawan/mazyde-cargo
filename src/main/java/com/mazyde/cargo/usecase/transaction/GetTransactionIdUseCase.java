package com.mazyde.cargo.usecase.transaction;

import com.mazyde.cargo.gateway.transaction.query.TransactionQueryGateway;
import com.mazyde.cargo.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransactionIdUseCase {

    private final TransactionQueryGateway transactionQueryGateway;

    public Transaction getTransaction(Long id) {
        return transactionQueryGateway.findById(id);
    }

}
