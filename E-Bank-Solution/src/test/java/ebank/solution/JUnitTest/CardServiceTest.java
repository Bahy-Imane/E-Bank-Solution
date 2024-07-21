//package ebank.solution.JUnitTest;
//
//import ebank.solution.model.Account;
//import ebank.solution.model.Card;
//import ebank.solution.repository.AccountRepository;
//import ebank.solution.repository.CardRepository;
//import ebank.solution.service.CardService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CardServiceTest {
//
//    @Mock
//    private CardRepository cardRepository;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private CardService cardService;
//
//    private Account account;
//    private Card card;
//
//    @BeforeEach
//    void setUp() {
//        account = new Account();
//        account.setAccountId(1L);
//
//        card = new Card();
//        card.setCardId(1L);
//        card.setNumber("1234567890123456");
//        card.setExpiryDate(new java.sql.Date(System.currentTimeMillis()));
//        card.setCardType("VISA");
//        card.setIsActive(true);
//        card.setIsBlocked(false);
//        card.setAccount(account);
//    }
//
//    @Test
//    public void testGetCards() {
//        when(cardRepository.findCardsByAccount_AccountId(1L)).thenReturn(Arrays.asList(card));
//
//        List<Card> cards = cardService.getCards(1L);
//
//        assertNotNull(cards);
//        assertEquals(1, cards.size());
//        assertEquals(card.getNumber(), cards.get(0).getNumber());
//
//        verify(cardRepository, times(1)).findCardsByAccount_AccountId(1L);
//    }
//
//    @Test
//    public void testAddCard() {
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
//        when(cardRepository.save(any(Card.class))).thenReturn(card);
//
//        Card newCard = cardService.addCard(1L, card);
//
//        assertNotNull(newCard);
//        assertEquals(card.getNumber(), newCard.getNumber());
//
//        verify(accountRepository, times(1)).findById(1L);
//        verify(cardRepository, times(1)).save(card);
//    }
//
//    @Test
//    public void testActivatedCard() {
//        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//        when(cardRepository.save(any(Card.class))).thenReturn(card);
//
//        Card activatedCard = cardService.activatedCard(1L);
//
//        assertNotNull(activatedCard);
//        assertFalse(activatedCard.getIsActive());
//
//        verify(cardRepository, times(1)).findById(1L);
//        verify(cardRepository, times(1)).save(card);
//    }
//
//    @Test
//    public void testDeactivatedCard() {
//        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//        when(cardRepository.save(any(Card.class))).thenReturn(card);
//
//        Card deactivatedCard = cardService.deactivatedCard(1L);
//
//        assertNotNull(deactivatedCard);
//        assertTrue(deactivatedCard.getIsActive());
//
//        verify(cardRepository, times(1)).findById(1L);
//        verify(cardRepository, times(1)).save(card);
//    }
//
//    @Test
//    public void testBlockCard() {
//        card.setBlockReason("Fraud");
//
//        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//        when(cardRepository.save(any(Card.class))).thenReturn(card);
//
//        Card blockedCard = cardService.blockCard(1L, "Fraud");
//
//        assertNotNull(blockedCard);
//        assertTrue(blockedCard.getIsBlocked());
//        assertEquals("Fraud", blockedCard.getBlockReason());
//
//        verify(cardRepository, times(1)).findById(1L);
//        verify(cardRepository, times(1)).save(card);
//    }
//
//    @Test
//    public void testBlockCardWithoutReason() {
//        card.setBlockReason(null);
//
//        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
//
//        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
//            cardService.blockCard(1L, "");
//        });
//
//        assertEquals("Block reason is empty", exception.getMessage());
//
//        verify(cardRepository, times(1)).findById(1L);
//        verify(cardRepository, never()).save(card);
//    }
//}
