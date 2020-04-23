package ru.naumow.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoomIdResolverImpl implements RoomIdResolver {

    @Autowired private Environment env;

    @Override
    public Long getRoomIdFromSessionAttributes(Map<String, Object> attributes) {
        return (Long) attributes.get(env.getRequiredProperty("chat.room.attribute.name"));
    }

    @Override
    public void putRoomIdIntoSessionAttributes(Map<String, Object> attributes, Long id) {
        attributes.put(env.getRequiredProperty("chat.room.attribute.name"), id);
    }
}
