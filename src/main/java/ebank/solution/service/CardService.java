package ebank.solution.service;
import ebank.solution.model.Account;
import ebank.solution.model.Card;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Card> getCards(Long accountId) {
        return cardRepository.findCardsByAccount_AccountId(accountId);
    }

    public Card addCard(Long accountId,Card card) {
        Account account = accountRepository.findById(accountId).get();
        card.setAccount(account);
        return cardRepository.save(card);
    }


    public Card isActivatedCard(Long cardId,Card card) {
        Card card1 = cardRepository.findById(cardId).get();
        card1.setIsActive(false);
        return cardRepository.save(card1);
    }


    public Card blockCard(Long cardId,String blockReason) {
        Card card = cardRepository.findById(cardId).get();
        card.setIsActive(false);
        if (card.getBlockReason() == null || card.getBlockReason().isEmpty()) {
            card.setBlockReason(blockReason);
            card.setIsBlocked(true);
        }
        return cardRepository.save(card);
    }

}
