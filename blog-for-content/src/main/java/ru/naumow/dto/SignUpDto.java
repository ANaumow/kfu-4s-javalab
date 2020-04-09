package ru.naumow.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String surname;
    private String vocation;
    private String email;
    private String password;
    private String blogAlias;
}
