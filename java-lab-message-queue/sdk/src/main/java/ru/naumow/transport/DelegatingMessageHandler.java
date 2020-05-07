package ru.naumow.transport;

public interface DelegatingMessageHandler {

    void delegateTo(MessageHandler handler);

}
