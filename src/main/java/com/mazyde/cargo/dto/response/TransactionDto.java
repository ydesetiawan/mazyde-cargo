package com.mazyde.cargo.dto.response;

import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private String name;

    private String destination;

    private String receiptNumber;

    private String courier;

    private String via;

    private String volume;

    private String weight;

    private String description;

    private TransactionStatus status;

    @With
    private String photo;

    public static TransactionDto valueOf(Transaction transaction) {
        return TransactionDto.builder()
            .id(transaction.getId())
            .name(transaction.getName())
            .destination(transaction.getDestination())
            .receiptNumber(transaction.getReceiptNumber())
            .courier(transaction.getCourier())
            .via(transaction.getVia())
            .volume(transaction.getVolume())
            .weight(transaction.getWeight())
            .description(transaction.getDescription())
            .status(transaction.getStatus())
            .build();
    }

}
