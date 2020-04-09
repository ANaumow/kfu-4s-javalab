package ru.naumow.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ModelAndView getInfo() {
        return null;
    }

}
