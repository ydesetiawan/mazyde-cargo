package com.mazyde.cargo.usecase.transaction;

import com.mazyde.cargo.gateway.transaction.query.TransactionQueryGateway;
import com.mazyde.cargo.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTransactionsUseCase {

    private final TransactionQueryGateway transactionQueryGateway;

    public Page<Transaction> getTransaction(Optional<String> receiptNumber, Pageable pageable) {
        if (receiptNumber.isPresent()) {
            return transactionQueryGateway.findAllByReceiptNumber(receiptNumber.get(), pageable);
        }
        return transactionQueryGateway.findAll(pageable);
    }

}
