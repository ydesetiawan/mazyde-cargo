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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SaveTransactionUseCase {

    private final TransactionCommandGateway transactionCommandGateway;
    private final TransactionPhotoCommandGateway transactionPhotoCommandGateway;

    private final TransactionQueryGateway transactionQueryGateway;
    private final UserQueryGateway userQueryGateway;

    @Transactional
    public void saveTransaction(SaveTransactionCmd cmd, MultipartFile file) {

        User user = userQueryGateway.findById(cmd.getUserId());
        Transaction transaction = getTransaction(user, cmd);

        transactionCommandGateway.save(transaction);

        if (null != file && !file.isEmpty()) {
            savePhoto(cmd, file, transaction);
        }

    }

    private void savePhoto(SaveTransactionCmd cmd, MultipartFile file, Transaction transaction) {
        try {

            BufferedImage resizeImage = resizeImage(
                ImageIO.read(file.getInputStream()),
                300,
                400
            );

            transactionPhotoCommandGateway.save(
                TransactionPhoto.valueOf(
                    transaction, toByteArray(resizeImage, "png")
                )
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("photo not valid");
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    private static byte[] toByteArray(BufferedImage bi, String format)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    private Transaction getTransaction(User user, SaveTransactionCmd cmd) {
        if (ActionType.EDIT.equals(cmd.getActionType())) {

            Transaction transaction = transactionQueryGateway.findById(cmd.getId());
            return transaction.editOf(cmd, user);
        }
        return Transaction.valueOf(cmd, user);

    }

}
