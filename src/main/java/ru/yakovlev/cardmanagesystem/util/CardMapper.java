package ru.yakovlev.cardmanagesystem.util;

import ru.yakovlev.cardmanagesystem.model.Card;
import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;

public class CardMapper {
    public static CardDTO toCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setCardNumber( card.getCardNumber());
        cardDTO.setBalance(card.getBalance());
        cardDTO.setActive(card.isActive());
        return cardDTO;
    }

    public static Card toCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setBalance(cardDTO.getBalance());
        card.setActive(cardDTO.isActive());
        return card;
    }
}
