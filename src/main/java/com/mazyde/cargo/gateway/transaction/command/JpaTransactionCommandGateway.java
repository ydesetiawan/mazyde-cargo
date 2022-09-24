package com.mazyde.cargo.gateway.transaction.command;

import com.mazyde.cargo.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaTransactionCommandGateway implements TransactionCommandGateway {

    @Override
    public void save(Transaction transaction) {

    }
}

