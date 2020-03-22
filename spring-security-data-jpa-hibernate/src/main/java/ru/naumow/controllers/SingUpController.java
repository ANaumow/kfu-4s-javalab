package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.dto.SignUpForm;
import ru.naumow.services.SignUpService;

@Controller
@RequestMapping("sign-up")
public class SingUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public ModelAndView getSignUp() {
        return new ModelAndView("sign_up");
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public String signUp(SignUpForm signUpForm) {
        signUpService.signUp(signUpForm);
        return "redirect:/sign-in";
    }

}
