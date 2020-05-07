package ru.naumow.jlmq.server.components.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.support.MissingSessionUserException;
import org.springframework.messaging.simp.user.DestinationUserNameProvider;
import org.springframework.stereotype.Component;
import ru.naumow.jlmq.server.annotation.Username;

import java.security.Principal;

@Component
public class UsernameMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Username.class) &&
                parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
        MessageHeaders headers = message.getHeaders();
        Principal principal = SimpMessageHeaderAccessor.getUser(headers);
        if (principal != null) {
            return (principal instanceof DestinationUserNameProvider ?
                    ((DestinationUserNameProvider) principal).getDestinationUserName() : principal.getName());
        }
        String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
        if (sessionId == null) {
            throw new MissingSessionUserException(message);
        }
        return sessionId;
    }

}
