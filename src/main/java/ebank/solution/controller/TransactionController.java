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


    @PostMapping("/interne/{accountId}/{toAcc}/{amount}/{description}")
    public void makeinterneTransaction(@PathVariable Long accountId,@PathVariable Long toAcc,@PathVariable Double amount,@PathVariable String description) {
         transactionService.transactionToInternAcc(accountId,toAcc,amount,description);
    }

    @PostMapping("/externe/{accountId}/{toAcc}/{amount}/{description}")
    public void makeExterneTransaction(@PathVariable Long accountId,@PathVariable Long toAcc,@PathVariable Double amount,@PathVariable String description) {
        transactionService.transactionToExternAcc(accountId,toAcc,amount,description);
    }
}
