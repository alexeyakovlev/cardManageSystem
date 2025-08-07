package ru.yakovlev.cardmanagesystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.cardmanagesystem.model.dto.CardDTO;
import ru.yakovlev.cardmanagesystem.service.CardService;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotEnoughBalance;
import ru.yakovlev.cardmanagesystem.util.exception.CardNotFoundException;
import ru.yakovlev.cardmanagesystem.util.exception.CardUnactiveException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO getCard(@PathVariable Long id) throws CardNotFoundException {
        return cardService.getCardById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardDTO> getCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO createCard(@RequestBody CardDTO cardDTO) {
        return cardService.createCard(cardDTO);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCard(@PathVariable Long id) throws CardNotFoundException {
        cardService.deleteCardById(id);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO updateCard(@PathVariable Long id, @RequestBody CardDTO cardDTO) throws CardNotFoundException {
        return cardService.updateCard(id, cardDTO);
    }

    @PostMapping("/deposit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO deposit(@PathVariable Long id, @RequestBody BigDecimal amount)
            throws CardNotFoundException, CardUnactiveException {
        return cardService.deposit(id, amount);
    }

    @PostMapping("/withdraw/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO withdraw(@PathVariable Long id, @RequestBody BigDecimal amount)
            throws CardNotFoundException, CardUnactiveException {
        return cardService.withdraw(id, amount);
    }

    @PostMapping("/transfer/{idSender}/{idReceiver}")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@PathVariable Long idSender, @PathVariable Long idReceiver, @RequestBody BigDecimal amount)
            throws CardNotFoundException, CardUnactiveException, CardNotEnoughBalance {
        cardService.transfer(idSender, idReceiver, amount);
    }

    @PostMapping("/block/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardDTO block(@PathVariable Long id) throws CardNotFoundException {
        return cardService.block(id);
    }
}
