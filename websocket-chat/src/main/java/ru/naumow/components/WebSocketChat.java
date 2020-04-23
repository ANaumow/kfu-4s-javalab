package ru.naumow.components;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.xml.soap.Text;

public interface WebSocketChat extends Chat<WebSocketSession, TextMessage> {
}
