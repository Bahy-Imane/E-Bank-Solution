package ebank.solution.JUnitTest;

import ebank.solution.model.Account;
import ebank.solution.model.Beneficiary;
import ebank.solution.model.Transaction;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.BeneficiaryRepository;
import ebank.solution.repository.TransactionRepository;
import ebank.solution.service.TransactionService;
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

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransactionToInternAcc_Success() {
        Account acc1 = new Account();
        acc1.setAccountId(1L);
        acc1.setBalance(1000.0);
        Account acc2 = new Account();
        acc2.setAccNumber(2L);
        acc2.setBalance(500.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(acc1));
        when(accountRepository.findAccountByAccNumber(2L)).thenReturn(acc2);

        transactionService.transactionToInternAcc(1L, 2L, 200.0, "Test transaction");

        verify(transactionRepository, times(2)).save(any(Transaction.class));
        verify(accountRepository, times(2)).save(any(Account.class));
        assertEquals(800.0, acc1.getBalance());
        assertEquals(700.0, acc2.getBalance());
    }

    @Test
    public void testTransactionToInternAcc_InsufficientFunds() {
        Account acc1 = new Account();
        acc1.setAccountId(1L);
        acc1.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(acc1));

        transactionService.transactionToInternAcc(1L, 2L, 200.0, "Test transaction");

        verify(transactionRepository, times(0)).save(any(Transaction.class));
        verify(accountRepository, times(0)).save(any(Account.class));
        assertEquals(100.0, acc1.getBalance());
    }

    @Test
    public void testTransactionToExternAcc_Success() {
        Account acc1 = new Account();
        acc1.setAccountId(1L);
        acc1.setBalance(1000.0);
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBenefAccNumber((double) 2L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(acc1));
        when(beneficiaryRepository.findByAccountNumber(2L)).thenReturn(beneficiary);

        transactionService.transactionToExternAcc(1L, 2L, 200.0, "Test transaction");

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).save(any(Account.class));
        assertEquals(800.0, acc1.getBalance());
    }

    @Test
    public void testTransactionToExternAcc_InsufficientFunds() {
        Account acc1 = new Account();
        acc1.setAccountId(1L);
        acc1.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(acc1));

        transactionService.transactionToExternAcc(1L, 2L, 200.0, "Test transaction");

        verify(transactionRepository, times(0)).save(any(Transaction.class));
        verify(accountRepository, times(0)).save(any(Account.class));
        assertEquals(100.0, acc1.getBalance());
    }

    @Test
    public void testGetTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());
        when(transactionRepository.findAccountByAccNumber(1L)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactions(1L);

        assertEquals(2, result.size());
        verify(transactionRepository, times(1)).findAccountByAccNumber(1L);
    }
}
