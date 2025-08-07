package ru.yakovlev.cardmanagesystem.service;

import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotEnoughBalance;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotFoundException;
import ru.yakovlev.cardmanagesystem.util.exception.CardUnactiveException;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {

    public CardDTO getCardById(Long id) throws CardNotFoundException;

    public List<CardDTO> getAllCards();

    public CardDTO createCard(CardDTO cardDTO);

    public void deleteCardById(Long id) throws CardNotFoundException;

    public CardDTO updateCard(Long id, CardDTO cardDTO) throws CardNotFoundException;

    public CardDTO deposit(Long id, BigDecimal amount) throws CardNotFoundException, CardUnactiveException;

    public CardDTO withdraw(Long id, BigDecimal amount) throws CardNotFoundException, CardUnactiveException;

    public void transfer(Long idCardSender, Long idCardReceiver, BigDecimal amount) throws CardNotFoundException, CardUnactiveException, CardNotEnoughBalance;

    public CardDTO block(Long cardId) throws CardNotFoundException;
}
