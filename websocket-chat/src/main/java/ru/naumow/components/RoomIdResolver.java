package ru.naumow.components;

import java.util.Map;

public interface RoomIdResolver {

    Long getRoomIdFromSessionAttributes(Map<String, Object> attributes);

    void putRoomIdIntoSessionAttributes(Map<String, Object> attributes, Long id);
}
