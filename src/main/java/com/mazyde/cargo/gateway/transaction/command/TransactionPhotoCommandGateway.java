package com.mazyde.cargo.gateway.transaction.command;

import com.mazyde.cargo.model.transaction.TransactionPhoto;

public interface TransactionPhotoCommandGateway {

    void save(TransactionPhoto transactionPhoto);

}
