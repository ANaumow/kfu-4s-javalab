package ru.naumow.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScans({
        @ComponentScan("ru.naumow.service"),
        @ComponentScan("ru.naumow.entity"),
        @ComponentScan("ru.naumow.repository")
})
public class AppConfig {
}
