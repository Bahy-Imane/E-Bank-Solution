package ebank.solution.service;

import ebank.solution.model.Account;
import ebank.solution.model.User;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;


    public List<Account> getAccounts(Long userId) {
        return accountRepository.findAccountByUserUserId(userId);
    }

    public Account addAccount(Long userId, Account account) {
        User user = userRepository.findById(userId).orElse(null);
            account.setUser(user);
            return accountRepository.save(account);
    }


    public void closeAccount(Long accountId, String reason) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account.getBalance() == 0) {
            account.setBlockRaison(reason);
            account.setIsActive(false);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("you have to Withdraw all your money");
        }
    }
}
