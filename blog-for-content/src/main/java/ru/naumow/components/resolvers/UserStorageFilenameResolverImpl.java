package ru.naumow.components.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.naumow.exceptions.BlogNotFoundException;
import ru.naumow.dto.BlogInfo;
import ru.naumow.entity.User;
import ru.naumow.services.BlogService;

import javax.annotation.PostConstruct;

@Component
public class UserStorageFilenameResolverImpl implements StorageFilenameResolver {

    @Autowired
    private Environment env;

    @Autowired
    private BlogService blogService;

    @Autowired
    private AuthResolver authResolver;

    private String localPath;
    private String publicPath;

    @PostConstruct
    public void init() {
        localPath = env.getProperty("storage.path.local", "c:/webapp/");
        publicPath = env.getProperty("storage.path.public", "/");
    }

    @Override
    public String localUrl(String filename) {
        return localPath() + filename;
    }

    @Override
    public String publicUrl(String filename) {
        return publicPath() + prefix() + filename;
    }

    @Override
    public String localPath() {
        return localPath + prefix();
    }

    @Override
    public String publicPath() {
        return publicPath;
    }

    private String prefix() {
        User user = authResolver.getUser();
        try {
            BlogInfo blogInfo = blogService.blogInfoByOwner(user);
            return blogInfo.getAlias() + "/";
        } catch (BlogNotFoundException e) {
            return "user-" + user.getId() + "/";
        }
    }

}
