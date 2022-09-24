package com.mazyde.cargo.gateway.transaction.command;

import com.mazyde.cargo.model.transaction.Transaction;

public interface TransactionCommandGateway {

    void save(Transaction transaction);

}
