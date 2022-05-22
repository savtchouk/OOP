package ru.spbstu.courseworkserver.main.exception;

public class PersonnelNotFoundException extends RuntimeException {
    public PersonnelNotFoundException(String message) {
        super(message);
    }
}
