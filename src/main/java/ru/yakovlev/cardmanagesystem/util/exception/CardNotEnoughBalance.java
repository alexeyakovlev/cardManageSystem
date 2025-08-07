package ru.yakovlev.cardmanagesystem.util.exception;

public class CardNotEnoughBalance extends RuntimeException {
    public CardNotEnoughBalance(String message) {
        super(message);
    }
}
