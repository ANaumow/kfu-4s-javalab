package ru.naumow.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.naumow.security.details.UserDetailsImpl;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/sign-in")
@Profile("mvc")
public class SingInController {

    @GetMapping
    public String getSingIn() {
        return "sign_in";
    }

}
