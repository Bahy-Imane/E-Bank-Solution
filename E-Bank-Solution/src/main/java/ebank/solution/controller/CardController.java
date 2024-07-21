package ebank.solution.controller;

import ebank.solution.model.Card;
import ebank.solution.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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

    @PutMapping("/activate/{cardId}")
    public Card activateCard(@PathVariable Long cardId) {
        return cardService.activatedCard(cardId);
    }

    @PutMapping("/deactivate/{cardId}")
    public Card deactivateCard(@PathVariable Long cardId) {
        return cardService.deactivatedCard(cardId);
    }

    @PutMapping("/{cardId}")
    public Card updateCard(@PathVariable Long cardId, @RequestParam String blockReason) {
        return cardService.blockCard(cardId,blockReason);
    }
}
