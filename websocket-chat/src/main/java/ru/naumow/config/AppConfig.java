package ru.naumow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScans({
        @ComponentScan("ru.naumow.repositories"),
        @ComponentScan("ru.naumow.entity"),
        @ComponentScan("ru.naumow.services"),
        @ComponentScan("ru.naumow.components"),
        @ComponentScan("ru.naumow.filters"),
        @ComponentScan("ru.naumow.handlers")
})
@PropertySources({
        @PropertySource("classpath:app.properties"),
        @PropertySource("classpath:commons-logging.properties")
})
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
