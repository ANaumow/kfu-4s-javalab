package ru.naumow.transport.stompless;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.naumow.interfaces.impls.DefaultJlmqMessage;
import ru.naumow.interfaces.impls.JlmqMessageDto;
import ru.naumow.transport.DelegatingMessageHandler;
import ru.naumow.transport.MessageHandler;
import ru.naumow.transport.TransportSession;

public class DelegatingWebSocketMessageHandler extends TextWebSocketHandler implements DelegatingMessageHandler {

    private final TransportSession transportSession;
    private final ObjectMapper mapper;

    private MessageHandler messageHandler;

    public DelegatingWebSocketMessageHandler(TransportSession transportSession, ObjectMapper mapper) {
        this.transportSession = transportSession;
        this.mapper = mapper;
    }

    @Override
    public void delegateTo(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JlmqMessageDto jlmqMessage = mapper.readValue(message.getPayload(), JlmqMessageDto.class);
        messageHandler.handleMessage(new DefaultJlmqMessage(transportSession, jlmqMessage));
    }

}
