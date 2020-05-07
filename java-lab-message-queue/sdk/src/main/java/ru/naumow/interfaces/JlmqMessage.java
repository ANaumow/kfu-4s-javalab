package ru.naumow.interfaces;

import ru.naumow.interfaces.impls.JlmqCommand;

public interface JlmqMessage {

    void accepted();

    void completed();

    Object getBody();

    JlmqCommand getCommand();

    String getQueueName();

    String getMessageId();

}
