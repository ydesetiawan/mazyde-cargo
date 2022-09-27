package com.mazyde.cargo.usecase.transaction;

import com.mazyde.cargo.controller.ActionType;
import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.gateway.transaction.command.TransactionCommandGateway;
import com.mazyde.cargo.gateway.transaction.query.TransactionQueryGateway;
import com.mazyde.cargo.gateway.user.query.UserQueryGateway;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveTransactionUseCase {

    private final TransactionCommandGateway transactionCommandGateway;

    private final TransactionQueryGateway transactionQueryGateway;

    private final UserQueryGateway userQueryGateway;

    @Transactional
    public void saveTransaction(Long userId, SaveTransactionCmd cmd) {

        User user = userQueryGateway.findById(userId);
        Transaction transaction = getTransaction(user, cmd);

        transactionCommandGateway.save(transaction);

    }

    private Transaction getTransaction(User user, SaveTransactionCmd cmd) {
        if (ActionType.EDIT.equals(cmd.getActionType())) {

            Transaction transaction = transactionQueryGateway.findById(cmd.getId());
            return transaction.editOf(cmd, user);
        }
        return Transaction.valueOf(cmd, user);

    }

}
