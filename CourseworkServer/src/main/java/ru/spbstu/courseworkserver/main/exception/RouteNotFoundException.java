package ru.spbstu.courseworkserver.main.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(String message) {
        super(message);
    }
}
