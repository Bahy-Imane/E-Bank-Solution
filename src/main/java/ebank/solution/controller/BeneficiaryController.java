package ebank.solution.controller;

import ebank.solution.model.Beneficiary;
import ebank.solution.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;


    @GetMapping("/{accountId}")
    public List<Beneficiary> getBeneficiaries(@PathVariable Long accountId) {
        return beneficiaryService.getAllBeneficiaries(accountId);
    }

    @PostMapping("/{accountId}")
    public Beneficiary addBeneficairy(@PathVariable Long accountId,Beneficiary beneficiary){
        return beneficiaryService.addBeneficiary(accountId,beneficiary);
    }

    @PutMapping("/benefId")
    public Beneficiary updateBenef(@PathVariable Long benefId,Beneficiary beneficiary){
        return beneficiaryService.updateBenifiary(benefId,beneficiary);
    }

    @DeleteMapping("/benefId")
    public void deleteBenefeciary (Long benefId){
        beneficiaryService.deleteBeneficiary(benefId);
    }
}
