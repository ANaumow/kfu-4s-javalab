package ru.naumow.interfaces;

import ru.naumow.transport.MessageHandler;

public interface JlmqConsumerBuilder {

    JlmqConsumerBuilder subscribe(String documents_for_generate);

    JlmqConsumerBuilder onReceive(MessageHandler handler);

    JlmqConsumer create();
}
