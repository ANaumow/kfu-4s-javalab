package ru.naumow.config;

import org.springframework.context.annotation.*;
import ru.naumow.security.config.SecurityConfig;

@Configuration
@Import(value = {SecurityConfig.class, PersistenceJpaConfig.class, WebConfig.class, AspectJConfig.class})
public class ApplicationListenerConfig {
}


