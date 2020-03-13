package ru.naumow.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.naumow.components.mail.MailComponent;
import ru.naumow.form.RegForm;
import ru.naumow.model.User;
import ru.naumow.model.UserStatus;
import ru.naumow.repositories.UserRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("confirmationMailProperties")
    private Properties properties;

    @Autowired
    @Qualifier("mailForConfirmationTemplate")
    private Template mailTemplate;

    @Override
    public void signUp(RegForm form) {
        String email = form.getEmail();
        String password = form.getPassword();

        boolean isEmailAvailable = userRepository.findByEmail(email).isEmpty();
        if (!isEmailAvailable) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        String confirmId = UUID.randomUUID().toString();
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .status(UserStatus.NOT_CONFIRMED)
                .confirmId(confirmId)
                .build();
        userRepository.save(user);

        sendConfirmationMail(email, confirmId);
    }

    private void sendConfirmationMail(String email, String confirmId) {
        Map<String, String> mailData = new HashMap<>();
        mailData.put("confirm_id", confirmId);

        StringWriter mailOut = new StringWriter();
/*
        System.out.println(mailTemplate.getOutputEncoding());
        System.out.println(mailTemplate);*/

        try {
            mailTemplate.process(mailData, mailOut);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        System.out.println(mailOut.toString());

        mailComponent.create()
                .withContent(mailOut.toString())
                .withSubject(properties.getProperty("subject"))
                .withContentType("text/html; charset=UTF-8")
                .withReceiver(email)
                .send();
    }

}
