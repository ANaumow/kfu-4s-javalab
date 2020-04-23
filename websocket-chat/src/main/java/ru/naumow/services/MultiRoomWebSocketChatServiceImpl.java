package ru.naumow.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.naumow.components.MultiRoomWebSocketChat;
import ru.naumow.components.RoomIdResolver;
import ru.naumow.components.WebSocketChat;
import ru.naumow.dto.MessageDto;
import ru.naumow.dto.RoomDto;
import ru.naumow.dto.RoomForm;
import ru.naumow.entity.Message;
import ru.naumow.entity.Room;
import ru.naumow.repositories.MessageRepository;
import ru.naumow.repositories.RoomRepository;
import ru.naumow.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class MultiRoomWebSocketChatServiceImpl implements MultiRoomWebSocketChatService {
    @Autowired private MultiRoomWebSocketChat multiRoomWebSocketChat;
    @Autowired private RoomRepository         roomRepository;
    @Autowired private MessageRepository      messageRepository;
    @Autowired private ObjectMapper           objectMapper;
    @Autowired private UserRepository         userRepository;
    @Autowired private RoomIdResolver         roomIdResolver;

    @Override
    public List<RoomDto> getRoomList(boolean loadMessages) {
        List<RoomDto> roomDtoList = RoomDto.from(roomRepository.findAll());
        if (loadMessages) {
            roomDtoList.forEach((roomDto) -> {
                List<Message> messageList = messageRepository.findAllByRoomId(roomDto.getId());
                roomDto.setMessageDtoList(MessageDto.from(messageList));
            });
        }
        return roomDtoList;
    }

    @Override
    public RoomDto getRoomById(Long roomId, boolean loadMessages) {
        Room room = roomRepository.findById(roomId).orElseThrow(IllegalArgumentException::new);
        RoomDto roomDto = RoomDto.from(room);
        if (loadMessages) {
            List<Message> messageList = messageRepository.findAllByRoomId(roomId);
            roomDto.setMessageDtoList(MessageDto.from(messageList));
        }
        return roomDto;
    }

    @Override
    public void createRoom(RoomForm roomForm) {
        Room room = Room.builder()
                .name(roomForm.getName())
                .build();
        roomRepository.save(room);
    }

    @Override
    public void handleMessage(WebSocketSession session, TextMessage textMessage) {
        try {
            String name = Objects.requireNonNull(session.getPrincipal()).getName();
            Long id = roomIdResolver.getRoomIdFromSessionAttributes(session.getAttributes());

            Room room = roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            MessageDto messageDto = objectMapper.readValue(textMessage.getPayload(), MessageDto.class);
            messageDto.setFromUsername(name);
            Message message = Message.builder()
                    .text(messageDto.getText())
                    .user(userRepository.findByLogin(name)
                            .orElseThrow(IllegalArgumentException::new))
                    .room(room)
                    .build();
            messageRepository.save(message);

            WebSocketChat currentChat = multiRoomWebSocketChat.instanceOfChatByRoomId(id);
            currentChat.sendMessage(session, new TextMessage(objectMapper.writeValueAsString(messageDto)));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void connectSession(WebSocketSession session) {
        Long id = roomIdResolver.getRoomIdFromSessionAttributes(session.getAttributes());
        WebSocketChat chat = multiRoomWebSocketChat.instanceOfChatByRoomId(id);
        chat.connect(session);
    }

    @Override
    public void disconnectSession(WebSocketSession session) {
        Long id = roomIdResolver.getRoomIdFromSessionAttributes(session.getAttributes());
        WebSocketChat chat = multiRoomWebSocketChat.instanceOfChatByRoomId(id);
        chat.disconnect(session);
    }

}
