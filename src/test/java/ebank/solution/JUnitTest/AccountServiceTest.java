package ebank.solution.JUnitTest;
import ebank.solution.model.Account;
import ebank.solution.model.User;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.UserRepository;
import ebank.solution.service.AccountService;
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


public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.add(new Account());
        when(accountRepository.findAccountByUserUserId(1L)).thenReturn(accounts);

        List<Account> result = accountService.getAccounts(1L);

        assertEquals(2, result.size());
        verify(accountRepository, times(1)).findAccountByUserUserId(1L);
    }

    @Test
    public void testAddAccount() {
        User user = new User();
        user.setUserId(1L);
        Account account = new Account();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.addAccount(1L, account);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        verify(userRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testCloseAccount() {
        Account account = new Account();
        account.setBalance(0.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.closeAccount(1L, "No longer needed");

        assertFalse(account.getIsActive());
        assertEquals("No longer needed", account.getBlockRaison());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testCloseAccountWithBalance() {
        Account account = new Account();
        account.setBalance(100.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.closeAccount(1L, "No longer needed");
        });

        assertEquals("you have to Withdraw all your money", exception.getMessage());
        verify(accountRepository, never()).save(account);
    }

    @Test
    public void testFindAccountByAccountNum() {
        Account account = new Account();
        account.setAccNumber(123456789L);
        when(accountRepository.findAccountByAccNumber(123456789L)).thenReturn(account);

        Account result = accountService.findAccountByAccountNum(123456789L);

        assertNotNull(result);
        assertEquals(123456789L, result.getAccNumber());
        verify(accountRepository, times(1)).findAccountByAccNumber(123456789L);
    }
}
