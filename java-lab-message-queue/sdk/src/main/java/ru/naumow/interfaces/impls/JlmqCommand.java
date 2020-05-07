package ru.naumow.interfaces.impls;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JlmqCommand {
    @JsonProperty("send") SEND,
    @JsonProperty("subscribe") SUBSCRIBE,
    @JsonProperty("receive") RECEIVE,
    @JsonProperty("accepted") ACCEPTED,
    @JsonProperty("completed") COMPLETED
}
