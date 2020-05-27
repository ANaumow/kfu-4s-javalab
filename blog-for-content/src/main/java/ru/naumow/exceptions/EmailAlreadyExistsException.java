package ru.naumow.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    private String emailName;

    public EmailAlreadyExistsException(String emailName) {
        this.emailName = emailName;
    }

    public String getEmailName() {
        return emailName;
    }

}
