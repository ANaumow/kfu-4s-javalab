package ru.naumow.mail;

import java.util.Properties;

public interface MailMessage {

    MailMessage withMailProperties(Properties prop);

    MailMessage withSenderMailName(String mail);

    MailMessage withSenderMailPassword(String password);

    MailMessage withReceiver(String mail);

    MailMessage withSubject(String subject);

    MailMessage withContentType(String type);

    MailMessage withContent(String content);

    void send();

}
