package ru.naumow.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.naumow.security.details.UserDetailsImpl;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("sign-in")
public class SingInController {

    @PostConstruct
    public void f() {
        System.out.println("Unnamed_blog-for-content_2");
    }

    @GetMapping
    public String getSingIn() {/*
        if (authentication != null) {
            String alias = ((UserDetailsImpl)authentication.getPrincipal()).getUser().getBlog().getAlias();
            return "redirect:" + alias;
        }*/
        return "sign_in";
    }
}
