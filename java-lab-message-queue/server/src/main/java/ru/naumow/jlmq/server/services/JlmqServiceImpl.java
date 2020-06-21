package ru.naumow.jlmq.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumow.jlmq.server.annotation.QueueStateChanges;
import ru.naumow.jlmq.server.components.generators.UuidGenerator;
import ru.naumow.jlmq.server.components.resolvers.LocalDateTimeResolver;
import ru.naumow.jlmq.server.dto.ConsumerDto;
import ru.naumow.jlmq.server.dto.JlmqMessageDto;
import ru.naumow.jlmq.server.entity.*;
import ru.naumow.jlmq.server.repositories.ConsumerRepository;
import ru.naumow.jlmq.server.repositories.MessageRepository;
import ru.naumow.jlmq.server.repositories.QueueRepository;

import java.util.List;

@Service
public class JlmqServiceImpl implements JlmqService {

    @Autowired private MessageRepository  messageRepository;
    @Autowired private ConsumerRepository consumerRepository;
    @Autowired private QueueRepository    queueRepository;

    @Autowired private LocalDateTimeResolver timeResolver;
    @Autowired private UuidGenerator         uuidGenerator;

    @QueueStateChanges
    @Override
    @Transactional
    public void processMessage(JlmqMessageDto messageDto, String username, String sessionId) {
        JlmqMessageDto.JlmqCommand command = messageDto.getCommand();
        switch (command) {
            case SEND:
                send(messageDto);
                break;
            case RECEIVE:
                throw new IllegalArgumentException("Command is not supported by server: " + command);
            case ACCEPTED:
                accepted(messageDto, username);
                break;
            case COMPLETED:
                completed(messageDto, username);
                break;
            case SUBSCRIBE:
                subscribe(messageDto, username, sessionId);
                break;
            default:
                throw new IllegalStateException("Command is not supported: " + command);
        }
    }

    @Override
    public List<ConsumerDto> getIdleConsumers() {
        return ConsumerDto.from(consumerRepository.findByState(ConsumerState.IDLE));
    }

    @Override
    public List<JlmqMessageDto> getAwaitingMessages() {
        return JlmqMessageDto.from(messageRepository.findByState(MessageState.AWAITING));
    }

    @Override
    public boolean isJobPresentFor(ConsumerDto consumerDto) {
        String consumerQueueName = consumerDto.getQueueName();
        List<JlmqMessageDto> awaitingMessages = getAwaitingMessages();
        for (JlmqMessageDto awaitingMessage : awaitingMessages) {
            String messageQueueName = awaitingMessage.getQueueName();
            if (consumerQueueName.equals(messageQueueName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JlmqMessageDto makeReceiveMessageFor(ConsumerDto consumerDto) {
        Message message = messageRepository
                .findFirstByStateOrderByCreatedAtAsc(MessageState.AWAITING)
                .orElseThrow();
        return JlmqMessageDto.builder()
                .messageId(message.getMessageId())
                .queueName(message.getQueue().getName())
                .command(JlmqMessageDto.JlmqCommand.RECEIVE)
                .body(message.getBody())
                .build();
    }

    @QueueStateChanges
    @Transactional
    @Override
    public void receiveMessageSentFor(ConsumerDto consumerDto) {
        Consumer consumer = consumerRepository.findByUsername(consumerDto.getUsername()).orElseThrow();
        consumer.setState(ConsumerState.ACCEPTING);
        consumerRepository.save(consumer);
    }

    public void send(JlmqMessageDto messageDto) {
        String body = messageDto.getBody() == null ? "" : (String) messageDto.getBody();
        Queue queue = queueRepository.findByName(messageDto.getQueueName()).orElseThrow();
        Message message = Message.builder()
                .createdAt(timeResolver.now())
                .messageId(uuidGenerator.generate().toString())
                .state(MessageState.AWAITING)
                .body(body)
                .queue(queue)
                .build();
        messageRepository.save(message);
    }

    public void subscribe(JlmqMessageDto messageDto, String username, String sessionId) {
        String queueName = messageDto.getQueueName();
        Queue queue = queueRepository.findByName(queueName).orElseThrow();
        Consumer consumer = Consumer.builder()
                .queue(queue)
                .state(ConsumerState.IDLE)
                .username(username)
                .sessionId(sessionId)
                .build();
        consumerRepository.save(consumer);
    }

    private void accepted(JlmqMessageDto messageDto, String username) {
        String messageId = messageDto.getMessageId();
        Message message = messageRepository.findByMessageId(messageId).orElseThrow();
        message.setState(MessageState.ACCEPTED);
        messageRepository.save(message);

        Consumer consumer = consumerRepository.findByUsername(username).orElseThrow();
        consumer.setState(ConsumerState.WORKING);
        consumerRepository.save(consumer);
    }

    private void completed(JlmqMessageDto messageDto, String username) {
        String messageId = messageDto.getMessageId();
        Message message = messageRepository.findByMessageId(messageId).orElseThrow();
        message.setState(MessageState.COMPLETED);
        messageRepository.save(message);

        Consumer consumer = consumerRepository.findByUsername(username).orElseThrow();
        consumer.setState(ConsumerState.IDLE);
        consumerRepository.save(consumer);
    }


}
