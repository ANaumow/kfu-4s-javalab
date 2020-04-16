package ru.naumow.components.resolvers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeResolverImpl implements LocalDateTimeResolver {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
