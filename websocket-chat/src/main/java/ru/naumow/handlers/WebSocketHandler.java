package ru.naumow.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.naumow.services.MultiRoomWebSocketChatService;

@Component
public class WebSocketHandler extends TextWebSocketHandler  {
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired private MultiRoomWebSocketChatService multiRoomChatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("webSocketHandler: " + session + " - " + message);
        }
        multiRoomChatService.handleMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("webSocketHandler: " + session + " is established");
        }
        multiRoomChatService.connectSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("webSocketHandler: " + session + " is closed with status " + status);
        }
        multiRoomChatService.disconnectSession(session);
    }
}
