package ru.naumow.components.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.naumow.model.UserSessionData;

import javax.annotation.PostConstruct;

@Component
public class UserStorageFilenameResolverImpl implements StorageFilenameResolver {

    @Autowired private Environment env;
    @Autowired private UserSessionData userSessionData;

    private String localPath;
    private String sharedPath;

    @PostConstruct
    public void init() {
        localPath = env.getProperty("storage.path.local", "c:/webapp/");
        sharedPath = env.getProperty("storage.path.shared", "/");
    }

    @Override
    public String localUrl(String filename) {
        return localPath() + filename;
    }

    @Override
    public String sharedUrl(String filename) {
        return sharedPath() + blogSubPath() + filename;
    }

    @Override
    public String asPostResource(String filename) {
        return blogSubPath() + filename;
    }

    @Override
    public String localPath() {
        return localPath + blogSubPath();
    }

    @Override
    public String sharedPath() {
        return sharedPath;
    }

    private String blogSubPath() {
        return userSessionData.getBlogAlias() + "/";
    }

}
