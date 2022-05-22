package ru.spbstu.courseworkserver.main.exception;

public class JournalNotFoundException extends RuntimeException {
    public JournalNotFoundException(String message) {
        super(message);
    }
}
