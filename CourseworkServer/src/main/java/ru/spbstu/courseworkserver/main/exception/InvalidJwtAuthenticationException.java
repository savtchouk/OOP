package ru.spbstu.courseworkserver.main.exception;

public class InvalidJwtAuthenticationException extends RuntimeException{
    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
