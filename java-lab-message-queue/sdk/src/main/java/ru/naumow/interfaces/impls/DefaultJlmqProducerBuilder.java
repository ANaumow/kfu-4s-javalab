package ru.naumow.interfaces.impls;

import ru.naumow.transport.TransportSession;
import ru.naumow.interfaces.JlmqProducer;
import ru.naumow.interfaces.JlmqProducerBuilder;

public class DefaultJlmqProducerBuilder implements JlmqProducerBuilder {

    private final TransportSession session;

    private String queueName;

    public DefaultJlmqProducerBuilder(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqProducerBuilder toQueue(String queueName) {
        this.queueName = queueName;
        return this;
    }

    @Override
    public JlmqProducer create() {
        return new DefaultJlmqProducer(session, queueName);
    }
}
