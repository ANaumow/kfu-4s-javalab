package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumow.dto.SignUpForm;
import ru.naumow.entity.User;
import ru.naumow.entity.UserStatus;
import ru.naumow.repositories.UsersRepository;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {
        String email = form.getEmail();
        String password = form.getPassword();

        boolean isEmailAvailable = usersRepository.findByEmail(email).isEmpty();
        if (!isEmailAvailable) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        String confirmId = UUID.randomUUID().toString();
        User user = User.builder()
                .email(email)
                .hashPassword(encodedPassword)
                .status(UserStatus.NOT_CONFIRMED)
                .build();
        usersRepository.save(user);
    }


}
