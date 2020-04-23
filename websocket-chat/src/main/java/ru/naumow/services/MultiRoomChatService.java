package ru.naumow.services;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.naumow.components.Chat;
import ru.naumow.components.MultiRoomChat;
import ru.naumow.dto.RoomForm;
import ru.naumow.dto.RoomDto;

import java.util.List;

public interface MultiRoomChatService<T, M> {

    List<RoomDto> getRoomList(boolean loadMessages);

    RoomDto getRoomById(Long roomId, boolean loadMessages);

    void createRoom(RoomForm roomForm);

    void connectSession(T speaker);

    void handleMessage(T speaker, M m);

    void disconnectSession(T speaker);

}
