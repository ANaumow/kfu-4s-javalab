package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumow.dto.SignInForm;
import ru.naumow.entity.User;
import ru.naumow.repositories.UsersRepository;

@Service
public class SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signIn(SignInForm form) {
        User user = usersRepository.findByEmail(form.getLogin()).orElseThrow(IllegalArgumentException::new);
        return passwordEncoder.matches(form.getPassword(), user.getHashPassword());
    }

}
