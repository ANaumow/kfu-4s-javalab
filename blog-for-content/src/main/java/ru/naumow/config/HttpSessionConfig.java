package ru.naumow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@EnableJdbcHttpSession
@Configuration
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {
}
