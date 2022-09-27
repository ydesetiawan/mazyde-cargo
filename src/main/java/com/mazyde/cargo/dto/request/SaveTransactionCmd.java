package com.mazyde.cargo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mazyde.cargo.controller.ActionType;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTransactionCmd {

    @With
    private ActionType actionType;

    private Long id;

    @NotEmpty(message = "No Resi tidak boleh kosong")
    private String receiptNumber;


    private String description;

    @NotEmpty(message = "Pengirim tidak boleh kosong")
    private String sender;

    @NotEmpty(message = "Telp Pengirim tidak boleh kosong")
    private String senderPhone;

    @NotEmpty(message = "Penerima tidak boleh kosong")
    private String receiver;

    @NotEmpty(message = "Telpn Penerima tidak boleh kosong")
    private String receiverPhone;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String receiverAddress;

    private String imageLink;

    @NotNull(message = "Ongkir tidak boleh kosong")
    private BigDecimal shippingCost;

    private TransactionStatus status;

    @With
    @JsonIgnore
    private Long userId;

    @With
    @JsonIgnore
    private MultipartFile multipartFile;

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
