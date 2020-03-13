package ru.naumow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.naumow.dto.UserDto;
import ru.naumow.form.SignInDto;
import ru.naumow.services.SignInService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping
    public ModelAndView getSignInView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signIn(HttpSession session, SignInDto signInDto) {
        UserDto userDto = signInService.signIn(signInDto);
        session.setAttribute("user-email", userDto.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/test");
        return modelAndView;
    }

}
