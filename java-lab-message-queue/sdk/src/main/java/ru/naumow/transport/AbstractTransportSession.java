package ru.naumow.transport;

import ru.naumow.interfaces.impls.JlmqCommand;
import ru.naumow.interfaces.impls.JlmqMessageDto;

public abstract class AbstractTransportSession implements TransportSession {

    @Override
    public void send(String queueName, String body) {
        JlmqMessageDto message = JlmqMessageDto.builder()
                .command(JlmqCommand.SEND)
                .queueName(queueName)
                .body(body)
                .build();
        processMessageSending(message);
        System.out.println("send");
    }

    @Override
    public void subscribe(String queueName) {
        JlmqMessageDto message = JlmqMessageDto.builder()
                .command(JlmqCommand.SUBSCRIBE)
                .queueName(queueName)
                .build();
        processMessageSending(message);
        System.out.println("subscribe");
    }

    @Override
    public void accept(String messageId) {
        JlmqMessageDto message = JlmqMessageDto.builder()
                .command(JlmqCommand.ACCEPTED)
                .messageId(messageId)
                .build();
        processMessageSending(message);
        System.out.println("accept");
    }

    @Override
    public void complete(String messageId) {
        JlmqMessageDto message = JlmqMessageDto.builder()
                .command(JlmqCommand.COMPLETED)
                .messageId(messageId)
                .build();
        processMessageSending(message);
        System.out.println("complete");
    }

    public abstract void onReceive(MessageHandler handler);

    protected abstract void processMessageSending(Object message);

}
