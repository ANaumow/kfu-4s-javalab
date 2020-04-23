package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumow.dto.SignInForm;
import ru.naumow.services.SecurityService;
import ru.naumow.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {
    @Autowired private Environment env;

    @Autowired private UserService     userService;
    @Autowired private SecurityService securityService;

    @GetMapping("sign-in")
    public String getSignInPage() {
        return "signIn";
    }

    @PostMapping("sign-in")
    public String signIn(HttpServletResponse response, SignInForm signInForm) {
        if (userService.signIn(signInForm)) {
            String token = securityService.createSecurityToken(signInForm.getLogin());
            Cookie cookie = new Cookie(env.getRequiredProperty("security.cookie.name"), token);
            response.addCookie(cookie);
            return "redirect:/rooms";
        } else {
            return "redirect:/sign-in?error";
        }
    }

}
