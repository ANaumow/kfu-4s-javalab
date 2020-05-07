package ru.naumow.transport.stompless;

import org.springframework.web.socket.client.WebSocketClient;
import ru.naumow.transport.TransportClient;
import ru.naumow.transport.TransportSession;
import ru.naumow.transport.TransportSettings;

public class StomplessTransportClientAdapter implements TransportClient {

    private final WebSocketClient  webSocketClient;

    public StomplessTransportClientAdapter(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @Override
    public TransportSession connect(TransportSettings settings) {
        return new StomplessTransportSessionAdapter(webSocketClient, settings);
    }

}
