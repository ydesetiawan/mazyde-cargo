package com.mazyde.cargo.usecase.transaction;

import com.mazyde.cargo.controller.ActionType;
import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.gateway.transaction.command.TransactionCommandGateway;
import com.mazyde.cargo.gateway.transaction.command.TransactionPhotoCommandGateway;
import com.mazyde.cargo.gateway.transaction.query.TransactionQueryGateway;
import com.mazyde.cargo.gateway.user.query.UserQueryGateway;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.transaction.TransactionPhoto;
import com.mazyde.cargo.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SaveTransactionUseCase {

    private final TransactionCommandGateway transactionCommandGateway;
    private final TransactionPhotoCommandGateway transactionPhotoCommandGateway;

    private final TransactionQueryGateway transactionQueryGateway;
    private final UserQueryGateway userQueryGateway;

    @Transactional
    public void saveTransaction(SaveTransactionCmd cmd) {

        User user = userQueryGateway.findById(cmd.getUserId());
        Transaction transaction = getTransaction(user, cmd);

        transactionCommandGateway.save(transaction);

        if (null != cmd.getMultipartFile() && !cmd.getMultipartFile().isEmpty()) {
            savePhoto(cmd, transaction);
        }

    }

    private void savePhoto(SaveTransactionCmd cmd, Transaction transaction) {
        try {
            transactionPhotoCommandGateway.save(
                TransactionPhoto.valueOf(
                    transaction,
                    cmd.getMultipartFile().getBytes()
                )
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("photo not valid");
        }
    }

    private Transaction getTransaction(User user, SaveTransactionCmd cmd) {
        if (ActionType.EDIT.equals(cmd.getActionType())) {

            Transaction transaction = transactionQueryGateway.findById(cmd.getId());
            return transaction.editOf(cmd, user);
        }
        return Transaction.valueOf(cmd, user);

    }

}
