package ru.naumow.security.api.jwt.service;


import ru.naumow.dto.SignInDto;
import ru.naumow.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
