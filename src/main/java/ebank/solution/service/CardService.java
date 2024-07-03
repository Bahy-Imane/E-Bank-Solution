package ebank.solution.service;


import ebank.solution.model.Card;
import ebank.solution.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;


    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card addCard(Card card) {
    return cardRepository.save(card);
    }


}
