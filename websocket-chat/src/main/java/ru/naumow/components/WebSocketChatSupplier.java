package ru.naumow.components;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public interface WebSocketChatSupplier extends Supplier<WebSocketChat> {

    @Lookup
    @Override
    WebSocketChat get();

}
