package ebank.solution.controller;


import ebank.solution.model.Transaction;
import ebank.solution.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/{accountId}")
    public List<Transaction> getTransaction(@PathVariable Long accountId) {
        return transactionService.getTransactions(accountId);
    }


    @PostMapping("/interne/{accountId}")
    public void makeInterneTransaction(@PathVariable Long accountId,
                                       @RequestParam Long toAcc,
                                       @RequestParam Double amount,
                                       @RequestParam String description) {
        transactionService.transactionToInternAcc(accountId, toAcc, amount, description);
    }


    @PostMapping("/externe/{accountId}/{toAcc}/{amount}/{description}")
    public void makeExterneTransaction(@PathVariable Long accountId,
                                       @RequestParam Long benefAccNumber,
                                       @RequestParam Double amount,
                                       @RequestParam String description) {
        transactionService.transactionToExternAcc(accountId,benefAccNumber,amount,description);
    }
}
