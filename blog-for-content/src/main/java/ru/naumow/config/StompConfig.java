package ru.naumow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {



    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // endpoint с которым работают клиенты, они могут посылать сюда сообщения
        // они могут их отсюда получать
        registry.enableSimpleBroker("/blog/update");
        //registry.enableSimpleBroker("/echo");
        // сюда можно направлять сообщения, чтобы они попали в само приложение
        //registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // сюда можно подключаться вебсокет-клиентам (stomp-клиент)
        registry.addEndpoint("/blog-for-content").setAllowedOrigins("*").withSockJS();
    }

}
