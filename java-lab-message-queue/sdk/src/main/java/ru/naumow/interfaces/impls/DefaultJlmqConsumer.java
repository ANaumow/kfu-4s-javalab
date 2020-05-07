package ru.naumow.interfaces.impls;

import lombok.Getter;
import ru.naumow.interfaces.JlmqConsumer;
import ru.naumow.transport.MessageHandler;
import ru.naumow.transport.TransportSession;

public class DefaultJlmqConsumer implements JlmqConsumer {
    private final TransportSession session;

    @Getter
    private final String queueName;

    @Getter
    private final MessageHandler handler;

    public DefaultJlmqConsumer(TransportSession session, String queueName, MessageHandler handler) {
        this.session = session;
        this.queueName = queueName;
        this.handler = handler;
    }

}
