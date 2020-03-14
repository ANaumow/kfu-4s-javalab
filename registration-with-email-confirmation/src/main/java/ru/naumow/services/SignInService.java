package ru.naumow.services;

import ru.naumow.dto.UserDto;
import ru.naumow.dto.AuthDto;

public interface SignInService {

    UserDto signIn(AuthDto authDto);

}
