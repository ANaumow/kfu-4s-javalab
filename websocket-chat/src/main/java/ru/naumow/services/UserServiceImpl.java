package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumow.dto.SignInForm;
import ru.naumow.dto.UserDto;
import ru.naumow.entity.User;
import ru.naumow.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserRepository  userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public boolean signIn(SignInForm signInForm) {
        User user = userRepository.findByLogin(signInForm.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        return passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword());
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found")));
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return UserDto.from(userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("user not found")));
    }
}
