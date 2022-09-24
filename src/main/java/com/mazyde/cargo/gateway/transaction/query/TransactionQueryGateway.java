package com.mazyde.cargo.gateway.transaction.query;

import com.mazyde.cargo.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionQueryGateway {

    Page<Transaction> findAll(Pageable pageable);

    Page<Transaction> findAllByReceiptNumber(String receiptNumber, Pageable pageable);

}
