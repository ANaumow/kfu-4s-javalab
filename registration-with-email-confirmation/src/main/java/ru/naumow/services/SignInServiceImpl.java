package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.naumow.dto.UserDto;
import ru.naumow.form.SignInDto;
import ru.naumow.model.User;
import ru.naumow.repositories.UserRepository;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto signIn(SignInDto form) {
        String email = form.getEmail();
        String password = form.getPassword();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        //String current = passwordEncoder.encode(password);
        String expected = candidate.getPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, expected);
        if (!isPasswordCorrect) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return UserDto.from(candidate);
    }

}
