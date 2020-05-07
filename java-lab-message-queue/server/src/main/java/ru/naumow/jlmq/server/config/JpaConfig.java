package ru.naumow.jlmq.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.naumow.jlmq.server.repositories")
public class JpaConfig {
}
