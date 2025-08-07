package ru.yakovlev.cardmanagesystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.cardmanagesystem.model.Card;
import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;
import ru.yakovlev.cardmanagesystem.repository.CardRepository;
import ru.yakovlev.cardmanagesystem.service.CardService;
import ru.yakovlev.cardmanagesystem.util.CardMapper;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotEnoughBalance;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotFoundException;
import ru.yakovlev.cardmanagesystem.util.exception.CardUnactiveException;

import java.math.BigDecimal;
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

    @Override
    @Transactional
    public CardDTO deposit(Long id, BigDecimal amount) throws CardNotFoundException, CardUnactiveException {
        Card cardById = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
        if (!cardById.isActive()) {
            throw new CardUnactiveException("Card with id " + id + " is not active");
        } else {
            cardById.setBalance(cardById.getBalance().add(amount));
            Card cardSave = cardRepository.save(cardById);
            return CardMapper.toCardDTO(cardSave);
        }
    }

    @Override
    @Transactional
    public CardDTO withdraw(Long id, BigDecimal amount) throws CardNotFoundException, CardUnactiveException {
        Card cardById = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
        if (!cardById.isActive()) {
            throw new CardUnactiveException("Card with id " + id + " is not active");
        } else {
            cardById.setBalance(cardById.getBalance().subtract(amount));
            Card cardSave = cardRepository.save(cardById);
            return CardMapper.toCardDTO(cardSave);
        }
    }

    @Override
    @Transactional
    public void transfer(Long idCardSender, Long idCardReceiver, BigDecimal amount) throws CardNotFoundException, CardUnactiveException, CardNotEnoughBalance {
        Card cardSenderById = cardRepository.findById(idCardSender)
                .orElseThrow(() -> new CardNotFoundException("Sender's card with id " + idCardSender + " not found"));
        Card cardReceiverById = cardRepository.findById(idCardReceiver)
                .orElseThrow(() -> new CardNotFoundException("Receiver's card with id " + idCardReceiver + " not found"));
        if (!cardSenderById.isActive()){
            throw new CardUnactiveException("Sender's card with id " + idCardSender + " is not active");
        } else if (!cardReceiverById.isActive()){
            throw new CardUnactiveException("Receiver's card with id " + idCardReceiver + " is not active");
        } else if (cardSenderById.getBalance().compareTo(amount) < 0) {
            throw new CardNotEnoughBalance("Receiver's card with id " + idCardReceiver + " is not enough balance");
        } else {
            cardSenderById.setBalance(cardSenderById.getBalance().subtract(amount));
            Card cardSenderSave = cardRepository.save(cardSenderById);
            cardReceiverById.setBalance(cardReceiverById.getBalance().add(amount));
            Card cardReceiverSave = cardRepository.save(cardReceiverById);
        }
    }
}
