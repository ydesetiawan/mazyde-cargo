package com.mazyde.cargo.model.transaction;

import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.model.user.User;
import com.mazyde.cargo.persistence.AuditingEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction", indexes = {@Index(name = "IDX_Transaction_1", columnList = "receiptNumber,sender,receiver")})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Transaction extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String receiptNumber;

    private String description;

    private String sender;

    private String senderPhone;

    private String receiver;

    private String receiverAddress;

    private String receiverPhone;

    private String imageLink;

    private BigDecimal shippingCost;

    @Enumerated
    @Column(columnDefinition = "tinyint")
    private TransactionStatus status;

    public static Transaction valueOf(SaveTransactionCmd cmd, User user) {
        return Transaction.builder()
            .user(user)
            .receiptNumber(cmd.getReceiptNumber())
            .description(cmd.getDescription())
            .sender(cmd.getSender())
            .senderPhone(cmd.getSenderPhone())
            .receiver(cmd.getReceiver())
            .receiverPhone(cmd.getReceiverPhone())
            .receiverAddress(cmd.getReceiverAddress())
            .shippingCost(cmd.getShippingCost())
            .status(cmd.getStatus())
            .build();
    }

    public Transaction editOf(SaveTransactionCmd cmd, User user) {

        this.receiptNumber = cmd.getReceiptNumber();
        this.user = user;
        this.description = cmd.getDescription();
        this.sender = cmd.getSender();
        this.senderPhone = cmd.getSenderPhone();
        this.receiver = cmd.getReceiver();
        this.receiverPhone = cmd.getReceiverPhone();
        this.receiverAddress = cmd.getReceiverAddress();
        this.shippingCost = cmd.getShippingCost();
        this.status = cmd.getStatus();
        return this;
    }


}
