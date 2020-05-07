package ru.naumow.transport;

import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import ru.naumow.transport.stompful.StompfulTransportClientAdapter;
import ru.naumow.transport.stompless.StomplessTransportClientAdapter;

import java.util.ArrayList;
import java.util.List;

public class DefaultTransportClientProvider implements TransportClientProvider {

    @Override
    public TransportClient provide(boolean usingStomp) {
        WebSocketClient sockJsClient = new SockJsClient(getSupportedTransports());
        return usingStomp ?
                new StompfulTransportClientAdapter(new WebSocketStompClient(sockJsClient)) :
                new StomplessTransportClientAdapter(sockJsClient);
    }

    private static List<Transport> getSupportedTransports() {
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        return transports;
    }

}
