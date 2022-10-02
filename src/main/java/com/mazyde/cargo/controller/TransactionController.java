package com.mazyde.cargo.controller;

import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.dto.response.TransactionDto;
import com.mazyde.cargo.model.transaction.Transaction;
import com.mazyde.cargo.model.user.UserInfoUtil;
import com.mazyde.cargo.usecase.transaction.GetTransactionIdUseCase;
import com.mazyde.cargo.usecase.transaction.GetTransactionPhotoUseCase;
import com.mazyde.cargo.usecase.transaction.GetTransactionsUseCase;
import com.mazyde.cargo.usecase.transaction.SaveTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final GetTransactionsUseCase getTransactionsUseCase;

    private final GetTransactionIdUseCase getTransactionIdUseCase;

    private final GetTransactionPhotoUseCase getTransactionPhotoUseCase;

    private final SaveTransactionUseCase saveTransactionUseCase;

    @GetMapping("/transactions")
    public String getTransactions(
        Model model,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("query") Optional<String> query) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(100);

        Page<Transaction> transactions = getTransactionsUseCase
            .getTransactions(
                query,
                PageRequest.of(
                    currentPage - 1,
                    pageSize,
                    Sort.by(Sort.Direction.DESC, "createdDate")
                )
            );

        model.addAttribute("transactionPage", transactions);

        return "transaction";
    }

    @GetMapping("/transactions/add")
    public String getTransactions(Model model) {

        model.addAttribute("cmd", new SaveTransactionCmd().withActionType(ActionType.ADD));

        return "add_transaction";
    }

    @GetMapping("/transactions/add/{transactionId}")
    public String getTransactionId(Model model, @PathVariable Long transactionId) {

        Transaction transaction = getTransactionIdUseCase.getTransaction(transactionId);
        model.addAttribute("cmd", SaveTransactionCmd.valueOf(transaction));

        return "add_transaction";
    }

    @GetMapping("/transactions/view/{transactionId}")
    public String getTransactionDetail(Model model, @PathVariable Long transactionId) {

        TransactionDto dto = getTransactionPhotoUseCase.getTransactionDetail(transactionId);
        model.addAttribute("dto", dto);

        return "transaction_detail.html";
    }

    @PostMapping("/transactions/add")
    public String saveTransaction(
        @RequestParam("photo") MultipartFile multipartFile,
        @ModelAttribute @Valid SaveTransactionCmd cmd,
        BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("result", result);
            model.addAttribute("cmd", cmd);
            return "add_transaction";
        }
        saveTransactionUseCase.saveTransaction(
            cmd
                .withUserId(UserInfoUtil.getUserId()), multipartFile
        );
        return "redirect:/transactions";
    }

}
