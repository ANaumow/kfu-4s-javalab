package ru.naumow.interfaces;

public interface JlmqProducerBuilder {

    JlmqProducerBuilder toQueue(String queueName);

    JlmqProducer create() ;

}
