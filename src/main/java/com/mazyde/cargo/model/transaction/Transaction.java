package com.mazyde.cargo.model.transaction;

import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.model.user.User;
import com.mazyde.cargo.persistence.AuditingEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction", indexes = {@Index(name = "IDX_Transaction_1", columnList = "destination,receiptNumber,via,courier")})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Transaction extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String destination;

    private String receiptNumber;

    private String courier;

    private String via;

    private String volume;

    private String weight;

    private String description;

    @Enumerated
    @Column(columnDefinition = "tinyint")
    private TransactionStatus status;

    public static Transaction valueOf(SaveTransactionCmd cmd, User user) {
        return Transaction.builder()
            .user(user)
            .name(cmd.getName())
            .destination(cmd.getDestination())
            .receiptNumber(cmd.getReceiptNumber())
            .courier(cmd.getCourier())
            .via(cmd.getVia())
            .volume(cmd.getVolume())
            .weight(cmd.getWeight())
            .description(cmd.getDescription())
            .status(cmd.getStatus())
            .build();
    }

    public Transaction editOf(SaveTransactionCmd cmd, User user) {
        this.user = user;
        this.name = cmd.getName();
        this.receiptNumber = cmd.getReceiptNumber();
        this.courier = cmd.getCourier();
        this.via = cmd.getVia();
        this.volume = cmd.getVolume();
        this.weight = cmd.getWeight();
        this.description = cmd.getDescription();
        this.status = cmd.getStatus();
        return this;
    }
    
}
