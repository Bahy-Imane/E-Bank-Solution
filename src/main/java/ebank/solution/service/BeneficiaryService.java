package ebank.solution.service;

import ebank.solution.model.Account;
import ebank.solution.model.Beneficiary;
import ebank.solution.model.User;
import ebank.solution.repository.AccountRepository;
import ebank.solution.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Beneficiary> getAllBeneficiaries(Long accountId) {
     return beneficiaryRepository.findBeneficiariesByAccount_AccountId(accountId);
    }

    public Beneficiary addBeneficiary(Long accountId,Beneficiary beneficiary) {
        Account account = accountRepository.findById(accountId).get();
        beneficiary.setAccount(account);
        return beneficiaryRepository.save(beneficiary);
    }

    public Beneficiary updateBenifiary(Long beneficiaryId,Beneficiary beneficiary) {
        Beneficiary beneficiary1=beneficiaryRepository.findById(beneficiaryId).get();
        beneficiary1.setBenefName(beneficiary.getBenefName());
        beneficiary1.setBenefAccNumber(beneficiary.getBenefAccNumber());
        beneficiary1.setAccount(beneficiary.getAccount());
        return beneficiaryRepository.save(beneficiary1);
    }

    public void deleteBeneficiary(Long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
    }
}
