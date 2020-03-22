package ru.naumow.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sign-in")
public class SingInController {

    @GetMapping
    public String getSingIn(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/all-users";
        }
        return "sign_in";
    }

}
