package ru.naumow.services;

import ru.naumow.dto.SignInForm;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;

public interface UserService {

    boolean signIn(SignInForm signInForm);

    UserDto getUserById(Long id);

    UserDto getUserByLogin(String login);


}
