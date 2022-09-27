package com.mazyde.cargo.dto.response;

import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private String receiptNumber;

    private String description;

    private String sender;

    private String senderPhone;

    private String receiver;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal shippingCost;

    private TransactionStatus status;

    @With
    private String photo;

    public static TransactionDto valueOf(Transaction transaction) {
        return TransactionDto.builder()
            .id(transaction.getId())
            .receiptNumber(transaction.getReceiptNumber())
            .description(transaction.getDescription())
            .sender(transaction.getSender())
            .senderPhone(transaction.getSenderPhone())
            .receiver(transaction.getReceiver())
            .receiverPhone(transaction.getReceiverPhone())
            .receiverAddress(transaction.getReceiverAddress())
            .shippingCost(transaction.getShippingCost())
            .status(transaction.getStatus())
            .build();
    }

}
