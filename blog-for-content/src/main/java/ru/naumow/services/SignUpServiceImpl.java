package ru.naumow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.components.resolvers.LocalDateTimeResolver;
import ru.naumow.dto.SignUpForm;
import ru.naumow.entity.User;
import ru.naumow.entity.UserRole;
import ru.naumow.entity.UserStatus;
import ru.naumow.exceptions.EmailAlreadyExistsException;
import ru.naumow.mail.MailComponent;
import ru.naumow.repositories.UsersRepository;

import java.util.Properties;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LocalDateTimeResolver timeResolver;

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    @Qualifier("confirmationMailProperties")
    private Properties properties;

    @Override
    @Transactional
    public void signUp(SignUpForm form) {
        String email = form.getEmail();
        String password = form.getPassword();

        boolean isEmailAvailable = !usersRepository.findByEmail(email).isPresent();
        if (!isEmailAvailable) {
            throw new EmailAlreadyExistsException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        String confirmId = UUID.randomUUID().toString();
        User user = User.builder()
                .role(UserRole.USER)
                .email(email)
                .name(form.getName())
                .surname(form.getSurname())
                .vocation(form.getVocation())
                .hashPassword(encodedPassword)
                .status(UserStatus.NOT_CONFIRMED)
                .avatarUrl("def/avatar")
                .createdAt(timeResolver.now())
                .confirmationUiid(confirmId)
                .build();
        usersRepository.save(user);

        mailComponent.create()
                .withContent("Hi! your confirmation link: " + "http://localhost:8080/confirm?id=" + confirmId)
                .withSubject(properties.getProperty("subject"))
                .withContentType("text/html; charset=UTF-8")
                .withReceiver(email)
                .send();
    }

    @Override
    @Transactional
    public void confirm(String confirmId) {
        User user = usersRepository.findByConfirmationUiid(confirmId).orElseThrow(() -> new IllegalStateException("User not found"));
        user.setStatus(UserStatus.CONFIRMED);
    }


}
