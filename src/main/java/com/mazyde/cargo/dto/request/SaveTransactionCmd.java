package com.mazyde.cargo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mazyde.cargo.controller.ActionType;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionStatus;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTransactionCmd {

    @With
    private ActionType actionType;

    private Long id;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;

    @NotEmpty(message = "Tujuan tidak boleh kosong")
    private String destination;

    @NotEmpty(message = "No Resi tidak boleh kosong")
    private String receiptNumber;

    @NotEmpty(message = "Kurir tidak boleh kosong")
    private String courier;

    @NotEmpty(message = "Via tidak boleh kosong")
    private String via;

    @NotEmpty(message = "Volume tidak boleh kosong")
    private String volume;

    @NotEmpty(message = "Berat tidak boleh kosong")
    private String weight;

    @NotEmpty(message = "Catatan tidak boleh kosong")
    private String description;

    private TransactionStatus status;

    @With
    @JsonIgnore
    private Long userId;

    @AssertTrue(message = "Id tidak tersedia dan tidak dapat edit data")
    public boolean isIdNullWhenEdit() {
        if (ActionType.EDIT.equals(getActionType())) {
            return !(null == id);
        }

        return true;
    }

    public static SaveTransactionCmd valueOf(Transaction transaction) {
        return SaveTransactionCmd.builder()
            .actionType(ActionType.EDIT)
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
