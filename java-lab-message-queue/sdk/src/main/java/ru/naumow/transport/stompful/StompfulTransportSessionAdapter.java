package ru.naumow.transport.stompful;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.naumow.transport.AbstractTransportSession;
import ru.naumow.transport.MessageHandler;
import ru.naumow.transport.TransportSettings;

import java.util.concurrent.ExecutionException;

public class StompfulTransportSessionAdapter extends AbstractTransportSession {
    private final StompSession        stompSession;
    private final TransportSettings   transportSettings;
    private final StompSessionHandler connectionHandler;

    private final DelegatingStompMessageHandler frameHandler;

    public StompfulTransportSessionAdapter(WebSocketStompClient stompClient, TransportSettings settings) {
        try {
            this.connectionHandler = new ConnectionHandler();
            this.frameHandler = new DelegatingStompMessageHandler(this);
            this.stompSession = stompClient.connect(settings.connectionUrl(), connectionHandler).get();
            this.stompSession.subscribe(settings.subscriptionUrl(), frameHandler);
            this.transportSettings = settings;
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void onReceive(MessageHandler handler) {
        this.frameHandler.delegateTo(handler);
    }

    @Override
    protected void processMessageSending(Object message) {
        stompSession.send(transportSettings.sendingUrl(), message);
    }

}
