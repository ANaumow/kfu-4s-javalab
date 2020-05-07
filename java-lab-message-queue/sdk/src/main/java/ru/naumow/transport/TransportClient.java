package ru.naumow.transport;

public interface TransportClient {

    TransportSession connect(TransportSettings setting);

}
