package ru.naumow;

import ru.naumow.interfaces.JlmqConnectorBuilder;
import ru.naumow.interfaces.impls.DefaultJlmqConnectorBuilder;
import ru.naumow.transport.DefaultTransportClientProvider;

public abstract class Jlmq {

    public static JlmqConnectorBuilder connector() {
        return new DefaultJlmqConnectorBuilder(new DefaultTransportClientProvider());
    }

}
