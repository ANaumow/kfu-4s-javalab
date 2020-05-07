package ru.naumow.transport;

public interface TransportClientProvider {

    TransportClient provide(boolean usingStomp);

}
