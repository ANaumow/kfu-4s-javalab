package ru.naumow.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.naumow.entity.User;
import ru.naumow.model.UserSessionData;
import ru.naumow.security.details.UserDetailsImpl;

/*
@Component
@Aspect
*/
public class OnAuthBlogAliasProvider {

    private final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    @Autowired
    private UserSessionData userSessionData;

    @EventListener
    public void handleSuccessAuthentication(InteractiveAuthenticationSuccessEvent event) {
        UserDetailsImpl userDetails = (UserDetailsImpl) event.getAuthentication().getPrincipal();

        threadLocal.set(userDetails.getUser());/*

        String blogAlias = userDetails.getUser()
                .getBlog()
                .getAlias();
*//*

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
*//*

        userSessionData.setBlogAlias(blogAlias);*/
    }



}
