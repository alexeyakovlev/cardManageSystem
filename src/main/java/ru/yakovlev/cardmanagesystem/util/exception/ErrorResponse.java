package ru.yakovlev.cardmanagesystem.util.exception;

public record ErrorResponse(String error, String message) {
    @Override
    public String toString() {
        return "Error: " + error + "\n" + ", Message: " + message;
    }
}
