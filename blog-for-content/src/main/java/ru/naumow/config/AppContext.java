package ru.naumow.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

@ComponentScans({
        @ComponentScan("ru.naumow.repositories"),
        @ComponentScan("ru.naumow.entity"),
        @ComponentScan("ru.naumow.services"),
        @ComponentScan("ru.naumow.components"),
        @ComponentScan("ru.naumow.handler")
})
@PropertySources({
        @PropertySource("classpath:blog.properties")
})
public class AppContext {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

