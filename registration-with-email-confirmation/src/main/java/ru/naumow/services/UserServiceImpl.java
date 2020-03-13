//package ru.naumow.services;
//
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.naumow.components.mail.MailComponent;
//import ru.naumow.form.SignInDto;
//import ru.naumow.form.RegForm;
//import ru.naumow.model.User;
//import ru.naumow.model.UserStatus;
//import ru.naumow.repositories.UserRepository;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.UUID;
//
//@Component
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private MailComponent mailComponent;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    @Qualifier("confirmationMailProperties")
//    private Properties properties;
//
//    @Autowired
//    @Qualifier("mailTemplate")
//    private Template mailTemplate;
//
//    @Override
//    public User signIn(SignInDto form) {
//        String email = form.getEmail();
//        String password = form.getPassword();
//
//        User candidate = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        String current = passwordEncoder.encode(password);
//        String expected = candidate.getPassword();
//        boolean isPasswordCorrect = passwordEncoder.matches(current, expected);
//        if (!isPasswordCorrect) {
//            throw new IllegalArgumentException("Incorrect password");
//        }
//
//        return candidate;
//    }
//
//    @Override
//    public void singUp(RegForm form) {
//        String email = form.getEmail();
//        String password = form.getPassword();
//
//        boolean isEmailAvailable = userRepository.findByEmail(email).isEmpty();
//        if (!isEmailAvailable) {
//            throw new IllegalArgumentException("Email already exists");
//        }
//
//        String confirmId = UUID.randomUUID().toString();
//        User user = User.builder()
//                .email(email)
//                .password(password)
//                .status(UserStatus.NOT_CONFIRMED)
//                .confirmId(confirmId)
//                .build();
//        userRepository.save(user);
//
//        sendConfirmationMail(email, confirmId);
//    }
//
//    private void sendConfirmationMail(String email, String confirmId) {
//        Map<String, String> mailData = new HashMap<>();
//        mailData.put("confirm_id", confirmId);
//
//        StringWriter mailOut = new StringWriter();
//
//        try {
//            mailTemplate.process(mailData, mailOut);
//        } catch (TemplateException | IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//        mailComponent.create()
//                .withContent(mailOut.toString())
//                .withSubject(properties.getProperty("subject"))
//                .withContentType("text/html")
//                .withReceiver(email)
//                .send();
//    }
//}
