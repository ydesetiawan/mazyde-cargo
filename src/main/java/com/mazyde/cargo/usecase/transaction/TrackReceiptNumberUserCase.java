package com.mazyde.cargo.usecase.transaction;

import com.mazyde.cargo.dto.response.TransactionDto;
import com.mazyde.cargo.gateway.transaction.query.TransactionPhotoQueryGateway;
import com.mazyde.cargo.gateway.transaction.query.TransactionQueryGateway;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TrackReceiptNumberUserCase {

    private final TransactionQueryGateway transactionQueryGateway;

    private final TransactionPhotoQueryGateway transactionPhotoQueryGateway;

    @Transactional(readOnly = true)
    public TransactionDto trackReceiptNumber(String receiptNumber) {

        Transaction transaction = transactionQueryGateway.findByReceiptNumber(receiptNumber);

        if (transaction == null) {
            return null;
        }

        TransactionPhoto photo = transactionPhotoQueryGateway.findFirstByTransactionId(transaction.getId());

        if (photo != null) {
            String photoBase64 = Base64.getEncoder().encodeToString(photo.getPhoto());
            return TransactionDto.valueOf(transaction)
                .withPhoto(photoBase64);
        }

        return TransactionDto.valueOf(transaction);

    }

}
