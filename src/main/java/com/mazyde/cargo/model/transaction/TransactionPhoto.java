package com.mazyde.cargo.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mazyde.cargo.persistence.AuditingEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_photo")
@EqualsAndHashCode(of = "id", callSuper = false)
public class TransactionPhoto extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Lob
    @JsonIgnore
    private byte[] photo;

    public static TransactionPhoto valueOf(Transaction transaction, byte[] photo) {
        return TransactionPhoto.builder()
            .transaction(transaction)
            .photo(photo)
            .build();
    }

}
