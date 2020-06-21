package ru.naumow.servlet.services.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.naumow.servlet.models.User;
import ru.naumow.servlet.models.dto.SignUpDto;
import ru.naumow.servlet.repositories.UserRepository;
import ru.naumow.servlet.services.MailSender;
import ru.naumow.servlet.services.SignUpService;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Configuration configuration;

    @Override
    public void signUp(SignUpDto dto) {
        String confirmLink = UUID.randomUUID().toString();
        String mailText = "'http://localhost:8080/confirm/" + confirmLink + "'";
        try {
            Template template = configuration.getTemplate("email_content.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("link", mailText);
            Writer out = new StringWriter();
            template.process(map, out);
            mailText = out.toString();
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .link(confirmLink)
                .state("NOT_CONFIRMED")
                .build();
        userRepository.save(user);

        MailSender mailSender = new MailSender(mailText, user.getEmail());
        mailSender.send();
    }
}
