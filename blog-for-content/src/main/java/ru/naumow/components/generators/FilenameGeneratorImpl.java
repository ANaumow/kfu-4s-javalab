package ru.naumow.components.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FilenameGeneratorImpl implements FilenameGenerator {

    private final IdGenerator idGenerator;

    @Value("${storage.filename.prefix}")
    private String prefix;
    @Value("${storage.filename.separator}")
    private String separator;

    public FilenameGeneratorImpl() {
        this.idGenerator = new IdGeneratorImpl();
    }

    public FilenameGeneratorImpl(@Autowired(required = false) IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public String generate() {
        return String.join(
                separator,
                prefix,
                Long.toString(System.currentTimeMillis()),
                idGenerator.generate().toString());
    }

}
