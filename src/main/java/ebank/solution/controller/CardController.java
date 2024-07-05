package ebank.solution.controller;

import ebank.solution.model.Card;
import ebank.solution.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{accountId}")
    public List<Card> getCards(@PathVariable Long accountId) {
        return cardService.getCards(accountId);
    }

    @PostMapping("/{accountId}")
    public Card addCard(@PathVariable Long accountId, @RequestBody Card card) {
        return cardService.addCard(accountId, card);
    }

    @PutMapping("/{cardId}")
    public Card updateCard(@PathVariable Long cardId, @RequestBody Card card) {
        return cardService.isActivatedCard(cardId, card);
    }


    @PutMapping("/{cardId}/{blockReason}")
    public Card updateCard(@PathVariable Long cardId, @PathVariable String blockReason) {
        return cardService.blockCard(cardId,blockReason);
    }
}
