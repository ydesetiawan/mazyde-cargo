package com.mazyde.cargo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String getLogin(
        @RequestParam(value = "error", required = false) String error,
        Model model,
        Authentication authentication
    ) {

        model.addAttribute("error", error);

        return "login";
    }

    @GetMapping("/home")
    public String getLogin(
        Model model
    ) {

        return "redirect:/transactions";
    }

    @GetMapping("/error")
    public String getError(
        Model model
    ) {

        return "error";
    }
}
