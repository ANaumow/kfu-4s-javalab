package ru.naumow.interfaces.impls;

import ru.naumow.interfaces.JlmqConsumer;
import ru.naumow.transport.MessageHandler;
import ru.naumow.transport.TransportSession;
import ru.naumow.interfaces.JlmqConsumerBuilder;

public class DefaultJlmqConsumerBuilder implements JlmqConsumerBuilder {

    private final TransportSession session;

    private String         queueName;
    private MessageHandler handler;

    public DefaultJlmqConsumerBuilder(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqConsumerBuilder subscribe(String queueName) {
        this.queueName = queueName;
        return this;
    }

    @Override
    public JlmqConsumerBuilder onReceive(MessageHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public JlmqConsumer create() {
        session.onReceive(handler);
        session.subscribe(queueName);
        return new DefaultJlmqConsumer(session, queueName, handler);
    }

}
