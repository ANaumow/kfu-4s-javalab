package ru.naumow.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Data
public class TestForm {

    @Min(4)
    private int positive;

    @Email(message = "{error.incorrect.mail}")
    private String email;

}
