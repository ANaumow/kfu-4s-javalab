package ru.naumow.components.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import ru.naumow.components.awares.BlogAliasAware;
import ru.naumow.model.UserSessionData;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@Component
public class UserStorageFilenameResolverImpl implements StorageFilenameResolver, BlogAliasAware, ServletContextAware {

    @Autowired
    private Environment env;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private UserSessionData userSessionData;

    private String localPath;
    private String sharedPath;
    private String hostname;

    @PostConstruct
    public void init() {
        localPath = env.getProperty("storage.path.local", "/res/uploads/");
        sharedPath = env.getProperty("storage.path.shared", "/res/uploads/");
        hostname = env.getRequiredProperty("hostname");
    }

    @Override
    public String localUrl(String filename) {
        return localPath() + filename;
    }

    @Override
    public String sharedUrl(String filename) {
        return sharedPath() + userSessionData.getBlogAlias() + "/" + filename;
    }

    @Override
    public String asPostResource(String filename) {
        return userSessionData.getBlogAlias() + "/" + filename;
    }

    @Override
    public String localPath() {
        return "C:/Soft/apache-tomcat-9.0.26/res/uploads/" + userSessionData.getBlogAlias() + "/";//servletContext.getRealPath(localPath + userSessionData.getBlogAlias() + "/");
    }

    @Override
    public String sharedPath() {
        return hostname + sharedPath;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void setBlogAlias(String alias) {
        userSessionData.setBlogAlias(alias);
    }
}
