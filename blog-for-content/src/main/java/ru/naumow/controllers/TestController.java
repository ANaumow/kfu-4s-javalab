package ru.naumow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    public void t(@RequestParam(value = "f", required = false) String f) {

    }

}
