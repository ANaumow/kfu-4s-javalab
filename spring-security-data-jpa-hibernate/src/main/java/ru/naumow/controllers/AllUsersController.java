package ru.naumow.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("all-users")
public class AllUsersController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getPage() {
        return "all_users";
    }

}
