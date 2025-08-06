package ru.yakovlev.cardmanagesystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.cardmanagesystem.model.Card;
import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;
import ru.yakovlev.cardmanagesystem.repository.CardRepository;
import ru.yakovlev.cardmanagesystem.service.CardService;
import ru.yakovlev.cardmanagesystem.util.CardMapper;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CardDTO getCardById(Long id) throws CardNotFoundException {
        Card cardById = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
        return CardMapper.toCardDTO(cardById);
    }

    @Override
    public List<CardDTO> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        List<CardDTO> cardDTOs = new ArrayList<>();
        for (Card card : cards) {
            cardDTOs.add(CardMapper.toCardDTO(card));
        }
        return cardDTOs;
    }

    @Override
    @Transactional
    public CardDTO createCard(CardDTO cardDTO) {
        Card card = CardMapper.toCard(cardDTO);
        card.setActive(true);
        Card cardSave = cardRepository.save(card);
        return CardMapper.toCardDTO(cardSave);
    }

    @Override
    @Transactional
    public void deleteCardById(Long id) throws CardNotFoundException {
        Card cardById = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
        cardRepository.delete(cardById);
    }

    @Override
    @Transactional
    public CardDTO updateCard(Long id, CardDTO cardDTO) throws CardNotFoundException {
        Card cardById = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
        cardDTO.setId(id);

        cardRepository.save(CardMapper.toCard(cardDTO));
        return cardDTO;
    }
}
