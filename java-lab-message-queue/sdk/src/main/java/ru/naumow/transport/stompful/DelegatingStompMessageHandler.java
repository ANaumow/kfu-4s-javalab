package ru.naumow.transport.stompful;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.util.Assert;
import ru.naumow.interfaces.impls.DefaultJlmqMessage;
import ru.naumow.interfaces.impls.JlmqMessageDto;
import ru.naumow.transport.DelegatingMessageHandler;
import ru.naumow.transport.MessageHandler;
import ru.naumow.transport.TransportSession;

import java.lang.reflect.Type;

public class DelegatingStompMessageHandler implements StompFrameHandler, DelegatingMessageHandler {

    private final TransportSession transportSession;

    private MessageHandler messageHandler;

    public DelegatingStompMessageHandler(TransportSession transportSession) {
        this.transportSession = transportSession;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return JlmqMessageDto.class;
    }

    @Override
    public void delegateTo(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Assert.notNull(payload, "Payload is null");
        Assert.isAssignable(payload.getClass(), JlmqMessageDto.class, "Type mismatch");
        DefaultJlmqMessage message = new DefaultJlmqMessage(transportSession, (JlmqMessageDto) payload);
        messageHandler.handleMessage(message);
    }

}
