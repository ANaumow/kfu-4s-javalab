package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.form.RegForm;
import ru.naumow.services.SignUpService;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public ModelAndView getSignUpView() {
        return new ModelAndView("sign_up");
    }

    @PostMapping
    public ModelAndView signUp(RegForm regForm) {
        signUpService.signUp(regForm);
        return getSignUpView();
    }

}
