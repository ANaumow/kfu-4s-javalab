package ru.naumow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.naumow.mail.MailComponent;
import ru.naumow.mail.PreparedMailComponent;

import javax.servlet.ServletContext;
import java.util.Properties;

@Configuration

@ComponentScans({
        @ComponentScan("ru.naumow.repositories"),
        @ComponentScan("ru.naumow.entity"),
        @ComponentScan("ru.naumow.services"),
        @ComponentScan("ru.naumow.components"),
        @ComponentScan("ru.naumow.handler"),
        @ComponentScan("ru.naumow.model")
})
@PropertySources({
        @PropertySource("classpath:blog.properties")
})
public class AppContext {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ServletContext servletContext;

    @Bean
    public freemarker.template.Configuration freemarkerConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        try {
            configuration.setServletContextForTemplateLoading(servletContext, "");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    @Bean
    public Properties mailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    @Bean
    public MailComponent mailComponent() {
        PreparedMailComponent mailComponent = new PreparedMailComponent();
        mailComponent.setSenderMailName("andrewnaumow@gmail.com");
        mailComponent.setSenderMailPassword("AndrewN009");
        mailComponent.setMailProperties(mailProperties());
        return mailComponent;
    }

    @Bean
    public Properties confirmationMailProperties() {
        Properties properties = new Properties();
        properties.put("subject", "Подтверждение почты");
        return properties;
    }


}

