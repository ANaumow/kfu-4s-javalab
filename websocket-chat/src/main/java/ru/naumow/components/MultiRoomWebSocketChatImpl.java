package ru.naumow.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class MultiRoomWebSocketChatImpl implements MultiRoomWebSocketChat {

    private final Map<Long, WebSocketChat> chatMap = new HashMap<>();

    @Autowired private WebSocketChatSupplier webSocketChatSupplier;

    @Override
    public WebSocketChat instanceOfChatByRoomId(Long roomId) {
        WebSocketChat chat;
        if ((chat = chatMap.get(roomId)) == null)
            chatMap.put(roomId, chat = webSocketChatSupplier.get());
        return chat;
    }


}
