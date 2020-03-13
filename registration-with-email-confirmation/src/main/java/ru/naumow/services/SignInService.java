package ru.naumow.services;

import ru.naumow.dto.UserDto;
import ru.naumow.form.SignInDto;

public interface SignInService {

    UserDto signIn(SignInDto signInDto);

}
