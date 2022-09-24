package com.mazyde.cargo.controller;

import com.mazyde.cargo.dto.request.SaveTransactionCmd;
import com.mazyde.cargo.usecase.transaction.GetTransactionsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final GetTransactionsUseCase getTransactionsUseCase;

    @GetMapping("/transactions")
    public String getTransactions(
        Model model,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("query") Optional<String> query) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(100);

        model.addAttribute("transactionPage", getTransactionsUseCase.getTransaction(query, PageRequest.of(currentPage - 1, pageSize)));

        return "transaction";
    }

    @GetMapping("/transactions/add")
    public String getTransactions(Model model) {

        return "add_transaction";
    }

    @PostMapping("/transactions/add")
    public String saveTransaction(@Valid SaveTransactionCmd cmd, Model model, BindingResult result) {
        if (null != result && result.hasErrors()) {
            model.addAttribute("fields", result);
            return "add_transaction";
        }

        System.out.println("lolos validasi");

        return "add_transaction";
    }

}
