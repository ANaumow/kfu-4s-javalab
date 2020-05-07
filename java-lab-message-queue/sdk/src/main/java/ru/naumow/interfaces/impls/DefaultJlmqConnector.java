package ru.naumow.interfaces.impls;

import ru.naumow.transport.TransportSession;
import ru.naumow.interfaces.JlmqConnector;
import ru.naumow.interfaces.JlmqConsumerBuilder;
import ru.naumow.interfaces.JlmqProducerBuilder;

public class DefaultJlmqConnector implements JlmqConnector {

    private final TransportSession session;

    public DefaultJlmqConnector(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqProducerBuilder producer() {
        return new DefaultJlmqProducerBuilder(session);
    }

    @Override
    public JlmqConsumerBuilder consumer() {
        return new DefaultJlmqConsumerBuilder(session);
    }
}
