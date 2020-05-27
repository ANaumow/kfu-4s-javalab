package ru.naumow.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignUpForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @Size(max = 10)
    @NotEmpty
    private String vocation;

    @Email
    @NotEmpty
    private String email;

    @Size(min = 3, max = 10)
    @NotEmpty
    private String password;

}
