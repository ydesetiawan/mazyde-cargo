package com.mazyde.cargo.dto.request;

import com.mazyde.cargo.model.transaction.TransactionStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTransactionCmd {

    @NotNull(message = "No Resi tidak boleh kosong")
    private String receiptNumber;


    private String description;

    @NotNull(message = "Pengirim tidak boleh kosong")
    private String sender;

    @NotNull(message = "Telp Pengirim tidak boleh kosong")
    private String senderPhone;

    @NotNull(message = "Penerima tidak boleh kosong")
    private String receiver;

    @NotNull(message = "Telpn Penerima tidak boleh kosong")
    private String receiverPhone;

    @NotNull(message = "Alamat tidak boleh kosong")
    private String receiverAddress;

    private String imageLink;

    @NotNull(message = "Ongkir tidak boleh kosong")
    private BigDecimal shippingCost;

    @NotNull(message = "Status tidak boleh kosong")
    private TransactionStatus status;

}
