package ru.yakovlev.cardmanagesystem.model.dto;

import java.math.BigDecimal;

public class CardDTO {

    private Long id;

    private Long cardNumber;

    private BigDecimal balance;

    private boolean active;

    public CardDTO(Long id, Long cardNumber, BigDecimal balance, boolean active) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.active = active;
    }

    public CardDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}