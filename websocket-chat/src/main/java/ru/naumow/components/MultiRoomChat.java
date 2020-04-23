package ru.naumow.components;

public interface MultiRoomChat<C extends Chat<?, ?>> {

    C instanceOfChatByRoomId(Long roomId);

}
