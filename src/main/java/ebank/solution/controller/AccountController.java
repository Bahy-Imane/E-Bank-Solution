package ebank.solution.controller;

import ebank.solution.model.Account;
import ebank.solution.model.User;
import ebank.solution.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{userId}")
    public List<Account> getAccounts(@PathVariable Long userId) {
       return accountService.getAccounts(userId);
    }


    @PostMapping("/{userId}")
    public Account addAccount(@PathVariable Long userId, @RequestBody Account account) {
        return accountService.addAccount(userId, account);
    }


    @PutMapping("/{accountId}/{reason}")
    public void closeAccount(@PathVariable Long accountId,@PathVariable String reason) {
        accountService.closeAccount(accountId, reason);
    }

}
