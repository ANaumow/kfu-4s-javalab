package ru.naumow.components.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FilenameGeneratorImpl implements FilenameGenerator {

    @Autowired
    private Environment env;

    private IdGenerator idGenerator;
    private String prefix;
    private String separator;

    public FilenameGeneratorImpl(@Autowired(required = false) IdGenerator idGenerator) {
        this.idGenerator = idGenerator == null? new IdGeneratorImpl() : idGenerator;
    }

    @PostConstruct
    public void init() {
        prefix = env.getProperty("storage.filename.prefix", "");
        separator = env.getProperty("storage.filename.separator", "");
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
