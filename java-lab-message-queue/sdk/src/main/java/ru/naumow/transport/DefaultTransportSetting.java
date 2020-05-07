package ru.naumow.transport;

public class DefaultTransportSetting implements TransportSettings {

    private String protocol = "http";
    private String ip = "localhost";
    private String endpoint = "/web-socket";
    private String topicUrl = "/user/jlmq";
    private String destUrl  = "/jlmq";
    private String port;

    public DefaultTransportSetting(String port) {
        this.port = port;
    }

    @Override
    public String connectionUrl() {
        return protocol + "://" + ip + ":" + port + endpoint;
    }

    @Override
    public String subscriptionUrl() {
        return topicUrl;
    }

    @Override
    public String sendingUrl() {
        return destUrl;
    }
}
