package com.mazyde.cargo.controller;

import com.mazyde.cargo.dto.response.TransactionDto;
import com.mazyde.cargo.usecase.transaction.TrackReceiptNumberUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TrackReceiptNumberUserCase trackReceiptNumberUserCase;

    @GetMapping("/")
    public String getIndex(Model model, @RequestParam("query") Optional<String> query) {

        if (query.isPresent()) {
            TransactionDto transactionDto = trackReceiptNumberUserCase.trackReceiptNumber(query.get());
            model.addAttribute("dto", transactionDto);
        }else {
            model.addAttribute("dto", null);
        }

        return "index2";
    }

}
