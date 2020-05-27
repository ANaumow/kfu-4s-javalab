package ru.naumow.mail;

import lombok.Setter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Setter
public class PreparedMailComponent implements MailComponent {

    private String     senderMailName;
    private String     senderMailPassword;
    private Properties mailProperties;

    @Override
    public MailMessage create() {
        return new BaseMailMessage()
                .withSenderMailName(senderMailName)
                .withSenderMailPassword(senderMailPassword)
                .withMailProperties(mailProperties);
    }

    public static class BaseMailMessage implements MailMessage {
        private Properties mailProperties;
        private String     senderMailName;
        private String     senderMailPassword;
        private String     receiver;
        private String     subject;
        private String     contentType;
        private String     content;

        @Override
        public BaseMailMessage withMailProperties(Properties mailProperties) {
            this.mailProperties = mailProperties;
            return this;
        }

        @Override
        public BaseMailMessage withSenderMailName(String senderMailName) {
            this.senderMailName = senderMailName;
            return this;
        }

        @Override
        public BaseMailMessage withSenderMailPassword(String senderMailPassword) {
            this.senderMailPassword = senderMailPassword;
            return this;
        }

        @Override
        public BaseMailMessage withReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        @Override
        public BaseMailMessage withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        @Override
        public BaseMailMessage withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        @Override
        public BaseMailMessage withContent(String content) {
            this.content = content;
            return this;
        }

        @Override
        public void send() {
            Session session = Session.getInstance(mailProperties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderMailName, senderMailPassword);
                }
            });

            try {

                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(senderMailName));

                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

                // Set Subject: header field
                message.setSubject(subject);

                // This mail has 2 part, the BODY and the embedded image
                MimeMultipart multipart = new MimeMultipart("related");

                // first part (the html)
                BodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setContent(content, contentType);
                // add it
                multipart.addBodyPart(messageBodyPart);

                // put everything together
                message.setContent(multipart);
                // Send message
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
