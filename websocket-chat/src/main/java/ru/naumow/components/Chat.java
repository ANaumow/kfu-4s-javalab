package ru.naumow.components;

public interface Chat<T, M> {

    void connect(T speaker);

    void sendMessage(T speaker, M message);

    void disconnect(T speaker);

}
