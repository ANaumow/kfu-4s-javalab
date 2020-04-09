package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.naumow.model.UserSessionData;
import ru.naumow.security.details.UserDetailsImpl;

@Controller
@RequestMapping("sign-in")
public class SingInController {


    @GetMapping
    public String getSingIn(Authentication authentication) {
        if (authentication != null) {
            String alias = ((UserDetailsImpl)authentication.getPrincipal()).getUser().getBlog().getAlias();
            return "redirect:" + alias;
        }
        return "signIn";
    }
}
