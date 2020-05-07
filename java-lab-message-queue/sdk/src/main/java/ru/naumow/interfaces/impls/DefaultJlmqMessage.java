package ru.naumow.interfaces.impls;

import ru.naumow.interfaces.JlmqMessage;
import ru.naumow.transport.TransportSession;

public class DefaultJlmqMessage implements JlmqMessage {
    private final TransportSession session;
    private final JlmqMessageDto   messageDto;

    public DefaultJlmqMessage(TransportSession session, JlmqMessageDto messageDto) {
        this.session = session;
        this.messageDto = messageDto;
    }

    @Override
    public void accepted() {
        session.accept(getMessageId());
    }

    @Override
    public void completed() {
        session.complete(getMessageId());
    }

    @Override
    public Object getBody() {
        return messageDto.getBody();
    }

    @Override
    public JlmqCommand getCommand() {
        return messageDto.getCommand();
    }

    @Override
    public String getQueueName() {
        return messageDto.getQueueName();
    }

    @Override
    public String getMessageId() {
        return messageDto.getMessageId();
    }
}
