package ru.naumow.jlmq.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.naumow.jlmq.server.util.HeadersUtil;
import ru.naumow.jlmq.server.annotation.SessionId;
import ru.naumow.jlmq.server.annotation.Username;
import ru.naumow.jlmq.server.dto.ConsumerDto;
import ru.naumow.jlmq.server.dto.JlmqMessageDto;
import ru.naumow.jlmq.server.events.IdleConsumerJobFoundEvent;
import ru.naumow.jlmq.server.services.JlmqService;

import java.util.concurrent.locks.Lock;

@Controller
@Profile("stompful")
public class JlmqStompfulController {
    @Autowired private SimpMessagingTemplate template;
    @Autowired private JlmqService jlmqService;

    private static final Object lock = new Object();

    @MessageMapping("/jlmq")
    public void handleJlmqMessage(
            @Payload JlmqMessageDto messageDto,
            @Username String username,
            @SessionId String sessionID
    ) {
        System.out.println(messageDto.getCommand());

        synchronized (lock) {
            jlmqService.processMessage(messageDto, username, sessionID);
        }

    }

    @EventListener
    public void handleIdleConsumer(IdleConsumerJobFoundEvent event) {
        ConsumerDto targetConsumer = event.getChangedConsumerDto();
        JlmqMessageDto messageDto = jlmqService.makeReceiveMessageFor(targetConsumer);
        template.convertAndSendToUser(
                targetConsumer.getUsername(),
                "/jlmq",
                messageDto,
                HeadersUtil.createHeaders(targetConsumer.getSessionId())
        );
        jlmqService.receiveMessageSentFor(targetConsumer);
    }

}
