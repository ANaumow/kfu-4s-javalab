package ru.naumow.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import ru.naumow.aspects.TestObjectPostProcessor;
import ru.naumow.model.UserSessionData;

@Configuration

@ComponentScans({
        @ComponentScan("ru.naumow.repositories"),
        @ComponentScan("ru.naumow.entity"),
        @ComponentScan("ru.naumow.model"),
        @ComponentScan("ru.naumow.services"),
        @ComponentScan("ru.naumow.components")
})
@PropertySources({
        @PropertySource("classpath:blog.properties")
})
public class AppContext {
}

