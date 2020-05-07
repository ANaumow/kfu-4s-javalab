package ru.naumow.jlmq.server.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import ru.naumow.jlmq.server.handlers.MyWebSocketHandler;

@Configuration
@EnableWebSocket
@Profile("stompless")
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired private MyWebSocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/web-socket")
                .setHandshakeHandler(defaultHandshakeHandler())
                .withSockJS();
    }

    @Bean
    public HandshakeHandler defaultHandshakeHandler() {
        return new DefaultHandshakeHandler();
    }


}
