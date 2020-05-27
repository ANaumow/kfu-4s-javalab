package ru.naumow.services;

import ru.naumow.dto.SignUpForm;

public interface SignUpService {

    void signUp(SignUpForm form);

    void confirm(String confirmId);

}
