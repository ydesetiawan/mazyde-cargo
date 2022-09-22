package com.mazyde.cargo.model;

import com.mazyde.cargo.persistence.AuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
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


}
