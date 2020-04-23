package ru.naumow.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;
import ru.naumow.components.RoomIdResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Component
public class SessionPreparationInterceptor implements HandshakeInterceptor {

    @Autowired private RoomIdResolver roomIdResolver;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        try {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            Long id = Long.valueOf(Objects.requireNonNull(WebUtils.findParameterValue(servletRequest, "r")));
            roomIdResolver.putRoomIdIntoSessionAttributes(attributes, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

}
