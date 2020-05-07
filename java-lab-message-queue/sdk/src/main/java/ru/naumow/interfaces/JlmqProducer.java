package ru.naumow.interfaces;

public interface JlmqProducer {

    void send(String body);

    String getQueueName();

}
