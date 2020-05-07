package ru.naumow.transport;

import ru.naumow.interfaces.JlmqMessage;

public interface MessageHandler {

    void handleMessage(JlmqMessage jlmqMessage);

}
