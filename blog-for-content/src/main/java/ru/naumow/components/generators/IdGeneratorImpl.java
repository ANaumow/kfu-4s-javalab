package ru.naumow.components.generators;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class IdGeneratorImpl implements IdGenerator {

    @Override
    public Long generate() {
        return ThreadLocalRandom.current().nextLong(Integer.MAX_VALUE);
    }

}
