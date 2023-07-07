package ru.otus.exception;

import java.io.IOException;

public class MessagePerSecondException extends RuntimeException {
    public MessagePerSecondException(String message) {
        super(message);
    }
}