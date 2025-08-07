package ru.yakovlev.cardmanagesystem.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yakovlev.cardmanagesystem.util.exception.*;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCardNotFoundException(CardNotFoundException ex) {
        return new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(CardUnactiveException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse handleCardUnactiveException(CardUnactiveException ex) {
        return new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(CardNotEnoughBalance.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse handleCardNotEnoughBalance(CardNotEnoughBalance ex) {
        return new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
    }
}
