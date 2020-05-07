package ru.naumow.jlmq.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.naumow.jlmq.server.components.resolvers.SessionIdMethodArgumentResolver;
import ru.naumow.jlmq.server.components.resolvers.UsernameMethodArgumentResolver;

import java.util.List;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
@Profile("stompful")
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired private UsernameMethodArgumentResolver  usernameMethodArgumentResolver;
    @Autowired private SessionIdMethodArgumentResolver sessionIdMethodArgumentResolver;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // endpoint с которым работают клиенты, они могут посылать сюда сообщения
        // они могут их отсюда получать
        registry.enableSimpleBroker("/jlmq");
        //registry.enableSimpleBroker("/echo");
        // сюда можно направлять сообщения, чтобы они попали в само приложение
        //registry.setApplicationDestinationPrefixes("/jlmq");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // сюда можно подключаться вебсокет-клиентам (stomp-клиент)
        registry.addEndpoint("/web-socket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(usernameMethodArgumentResolver);
        argumentResolvers.add(sessionIdMethodArgumentResolver);
    }

}
