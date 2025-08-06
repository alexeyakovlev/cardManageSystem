package ru.yakovlev.cardmanagesystem.service;

import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotFoundException;

import java.util.List;

public interface CardService {

    public CardDTO getCardById(Long id) throws CardNotFoundException;

    public List<CardDTO> getAllCards();

    public CardDTO createCard(CardDTO cardDTO);

    public void deleteCardById(Long id) throws CardNotFoundException;

    public CardDTO updateCard(Long id, CardDTO cardDTO) throws CardNotFoundException;
}
