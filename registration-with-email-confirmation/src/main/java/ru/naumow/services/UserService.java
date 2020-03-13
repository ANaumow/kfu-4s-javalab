package ru.naumow.services;

import ru.naumow.form.SignInDto;
import ru.naumow.form.RegForm;
import ru.naumow.model.User;

public interface UserService {

    User signIn(SignInDto form);

    void singUp(RegForm form);

}
