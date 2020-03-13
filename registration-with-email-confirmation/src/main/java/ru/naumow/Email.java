package ru.naumow;

public class Email {

    public static void main(String[] args) {
        Sender tlsSender = new Sender("andrewnaumow@gmail.com", "AndrewN008");

        tlsSender.send(
                "Это тема письма",
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                        "    <title>Untitled</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"confirm_mail.css\">\n" +
                        "</head>\n" +
                        "\n" +
                        "<body style=\"background-color: #ffffff;\">\n" +
                        "<h1 style=\"background-color: #00da64;padding: 11px;\">Spring mail test</h1>\n" +
                        "<p style=\"font-weight: bold;\">Письмо подтверждения для </p>\n" +
                        "<p style=\"font-weight: bold;\">Что бы подтвердить перейдите по ссылке:</p>\n" +
                        "<p style=\"background-color: #eaeaea;\">ссылка</p>\n" +
                        "<div style=\"height: 23px;background-color: #00da64;\"></div>\n" +
                        "<img src=\"cid:image\" width='100' height='100'>" +
                        "</body>\n" +
                        "\n" +
                        "</html>",
                "anaumow17@gmail.com");

    }

}
