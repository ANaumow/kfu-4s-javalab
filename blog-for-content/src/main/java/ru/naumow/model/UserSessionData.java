package ru.naumow.model;

import lombok.Data;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.naumow.entity.User;

import java.util.UUID;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSessionData {

    private User user;
    private String blogAlias;


}
