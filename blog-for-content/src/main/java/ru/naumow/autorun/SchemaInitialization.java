package ru.naumow.autorun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SchemaInitialization {

    @Autowired
    private Environment env;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Value("classpath:schema.sql")
    private Resource resource;

    @PostConstruct
    public void initSchemas() {
        String property = env.getProperty("spring.datasource.initialization-mode");
        boolean doInit;
        doInit = property != null && property.equals("always");
        if (doInit) {
            try {
                String s = new String(Files.readAllBytes(Paths.get(resource.getFile().getPath())));
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                entityManager.getTransaction().begin();
                entityManager.createNativeQuery(s).executeUpdate();
                entityManager.getTransaction().commit();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
