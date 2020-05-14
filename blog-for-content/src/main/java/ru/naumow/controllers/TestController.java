package ru.naumow.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.naumow.dto.TestForm;

import javax.validation.Valid;

import org.springframework.web.servlet.view.freemarker.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @PostMapping("/api/test")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody TestForm form) {
        return ResponseEntity.ok("All ok");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @GetMapping("test")
    public String t(Model model) {
        model.addAttribute("testForm", new TestForm());
        return "test";
    }

    @PostMapping("test")
    public String doPost(@Valid TestForm form, BindingResult bindingResult) {
        return "test";
    }

}
