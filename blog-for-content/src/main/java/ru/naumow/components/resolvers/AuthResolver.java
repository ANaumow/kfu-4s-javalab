package ru.naumow.components.resolvers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.naumow.entity.User;
import ru.naumow.security.details.UserDetailsImpl;

@Component
public class AuthResolver {

    public User getUser() {
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}
