package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.dto.SignUpDto;
import ru.naumow.services.SignUpService;

@Controller
@Profile("mvc")
public class SingUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign_up";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-up")
    public String signUp(SignUpDto signUpDto) {
        signUpService.signUp(signUpDto);
        return "redirect:sign-in";
    }

}
