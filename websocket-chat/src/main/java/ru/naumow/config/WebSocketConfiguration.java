package ru.naumow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import ru.naumow.handlers.SessionPreparationInterceptor;
import ru.naumow.handlers.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired private WebSocketHandler              handler;
    @Autowired private SessionPreparationInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/chat")
                .setHandshakeHandler(defaultHandshakeHandler())
                .addInterceptors(interceptor)
                .withSockJS()
                .setWebSocketEnabled(false);
    }

    @Bean
    public HandshakeHandler defaultHandshakeHandler() {
        return new DefaultHandshakeHandler();
    }

}
