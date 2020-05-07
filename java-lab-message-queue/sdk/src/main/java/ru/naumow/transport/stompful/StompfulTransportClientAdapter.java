package ru.naumow.transport.stompful;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.naumow.transport.TransportClient;
import ru.naumow.transport.TransportSession;
import ru.naumow.transport.TransportSettings;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class StompfulTransportClientAdapter implements TransportClient {

    private final WebSocketStompClient stompClient;

    public StompfulTransportClientAdapter(WebSocketStompClient stompClient) {
        this.stompClient = stompClient;
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        this.stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
    }

    @Override
    public TransportSession connect(TransportSettings settings) {
        return new StompfulTransportSessionAdapter(stompClient, settings);
    }

}
