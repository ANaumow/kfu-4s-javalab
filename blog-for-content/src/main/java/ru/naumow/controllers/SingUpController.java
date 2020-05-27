package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumow.dto.SignUpForm;
import ru.naumow.exceptions.EmailAlreadyExistsException;
import ru.naumow.services.SignUpService;

import javax.validation.Valid;

@Controller
@Profile("mvc")
public class SingUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpForm signUpForm,  BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                signUpService.signUp(signUpForm);
                return "redirect:sign-in";
            } catch (EmailAlreadyExistsException e) {
                model.addAttribute("signUpForm", signUpForm);
                model.addAttribute("err", "EmailAlreadyExistsException: " + e.getEmailName());
                return "sign_up";
            }
        } else {
            model.addAttribute("signUpForm", signUpForm);
            return "sign_up";
        }
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("id") String confirmId) {
        signUpService.confirm(confirmId);
        return "redirect:/sign-in";
    }

}
