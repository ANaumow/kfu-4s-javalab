package ru.naumow.jlmq.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.naumow.jlmq.server.dto.ConsumerDto;
import ru.naumow.jlmq.server.dto.JlmqMessageDto;
import ru.naumow.jlmq.server.events.IdleConsumerJobFoundEvent;
import ru.naumow.jlmq.server.services.JlmqService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("stompless")
public class MyWebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Autowired
    private JlmqService jlmqService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JlmqMessageDto jlmqMessageDto = objectMapper.readValue(message.getPayload(), JlmqMessageDto.class);
        String sessionId = session.getId();
        String username = session.getPrincipal() == null ?
            session.getId() : session.getPrincipal().getName();
        jlmqService.processMessage(jlmqMessageDto, username, sessionId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    @EventListener
    public void handleIdleConsumer(IdleConsumerJobFoundEvent event) {
        ConsumerDto targetConsumer = event.getChangedConsumerDto();
        JlmqMessageDto messageDto = jlmqService.makeReceiveMessageFor(targetConsumer);

        for (WebSocketSession session : sessions) {
            if (session.getId().equals(targetConsumer.getSessionId())) {
                try {
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDto)));
                    jlmqService.receiveMessageSentFor(targetConsumer);
                    return;
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

    }

}
