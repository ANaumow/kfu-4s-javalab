package ru.naumow.interfaces;

public interface JlmqConnector {

    JlmqProducerBuilder producer();

    JlmqConsumerBuilder consumer();

}
