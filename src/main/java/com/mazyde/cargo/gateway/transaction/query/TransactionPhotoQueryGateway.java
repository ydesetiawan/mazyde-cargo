package com.mazyde.cargo.gateway.transaction.query;

import com.mazyde.cargo.model.transaction.TransactionPhoto;

public interface TransactionPhotoQueryGateway {

    TransactionPhoto findFirstByTransactionId(Long transactionId);

}
