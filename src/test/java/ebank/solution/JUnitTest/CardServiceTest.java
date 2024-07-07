package ebank.solution.JUnitTest;

import ebank.solution.model.Account;
import ebank.solution.model.Card;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.CardRepository;
import ebank.solution.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        when(cardRepository.findCardsByAccount_AccountId(1L)).thenReturn(cards);

        List<Card> result = cardService.getCards(1L);

        assertEquals(2, result.size());
        verify(cardRepository, times(1)).findCardsByAccount_AccountId(1L);
    }

    @Test
    public void testAddCard() {
        Account account = new Account();
        account.setAccountId(1L);
        Card card = new Card();
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.addCard(1L, card);

        assertNotNull(result);
        assertEquals(account, result.getAccount());
        verify(accountRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testIsActivatedCard() {
        Card card = new Card();
        card.setIsActive(true);
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.isActivatedCard(1L, card);

        assertNotNull(result);
        assertFalse(result.getIsActive());
        verify(cardRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testBlockCard() {
        Card card = new Card();
        card.setBlockReason(null);
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            cardService.blockCard(1L, "Lost card");
        });

        assertEquals("Block reason is empty", exception.getMessage());
        verify(cardRepository, never()).save(card);
    }

    @Test
    public void testBlockCardWithReason() {
        Card card = new Card();
        card.setBlockReason("Lost card");
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(card)).thenReturn(card);

        Card result = cardService.blockCard(1L, "Lost card");

        assertNotNull(result);
        assertFalse(result.getIsActive());
        assertTrue(result.getIsBlocked());
        assertEquals("Lost card", result.getBlockReason());
        verify(cardRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).save(card);
    }
}
