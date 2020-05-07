package ru.naumow.interfaces;

import ru.naumow.transport.MessageHandler;

public interface JlmqConsumer {

    String getQueueName();

    MessageHandler getHandler();

}
