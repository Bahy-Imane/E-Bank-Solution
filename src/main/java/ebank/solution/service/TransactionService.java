package ebank.solution.service;
import ebank.solution.model.Account;
import ebank.solution.model.Beneficiary;
import ebank.solution.model.Transaction;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.BeneficiaryRepository;
import ebank.solution.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;


    public void transactionToInternAcc(Long accountId, Long toAcc, Double amount, String description) {
        Account acc1 =accountRepository.findById(accountId).orElse(null);
        Account acc2 =accountRepository.findAccountByAccNumber(toAcc);


        if (acc1.getBalance()<amount) {
            System.out.println("your balance is less than your amount");
        }else {
            acc1.setBalance(acc1.getBalance()-amount);
            accountRepository.save(acc1);
            Transaction transactionDebit = new Transaction();
            transactionDebit.setDescription(description);
            transactionDebit.setTransType("Debit");
            transactionDebit.setAmount(amount);
            transactionDebit.setTransDateTime(new Date(System.currentTimeMillis()));
            transactionDebit.setAccount(acc1);
            transactionDebit.setTarget(toAcc);
            transactionRepository.save(transactionDebit);


            acc2.setBalance(acc2.getBalance()+amount);
            accountRepository.save(acc2);
            Transaction transactionCredit = new Transaction();
            transactionCredit.setDescription(description);
            transactionCredit.setTransType("Credit");
            transactionCredit.setAmount(amount);
            transactionCredit.setTransDateTime(new Date(System.currentTimeMillis()));
            transactionCredit.setAccount(acc2);
            transactionCredit.setTarget(acc1.getAccNumber());
            transactionRepository.save(transactionCredit);
        }

    }

    public void transactionToExternAcc(Long accountId,Long toAcc, Double amount, String description) {
        Account acc1 =accountRepository.findById(accountId).orElse(null);
        Beneficiary benef =beneficiaryRepository.findByAccountNumber(toAcc);


        if (acc1.getBalance()<amount) {
            System.out.println("your balance is less than your amount");
        }else {
            acc1.setBalance(acc1.getBalance()-amount);
            accountRepository.save(acc1);
            Transaction transactionDebit = new Transaction();
            transactionDebit.setDescription(description);
            transactionDebit.setTransType("Debit");
            transactionDebit.setAmount(amount);
            transactionDebit.setTransDateTime(new Date(System.currentTimeMillis()));
            transactionDebit.setAccount(acc1);
            transactionDebit.setBeneficiary(benef);
            transactionRepository.save(transactionDebit);

        }

    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findAccountByAccNumber(accountId);
    }

}
