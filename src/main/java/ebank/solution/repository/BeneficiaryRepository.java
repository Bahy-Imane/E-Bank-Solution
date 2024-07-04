package ebank.solution.repository;

import ebank.solution.model.Account;
import ebank.solution.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    @Query(value = "select * from beneficiary where account_id = ?", nativeQuery = true)
    List<Beneficiary> findBeneficiariesByAccount_AccountId(Long accountId);
}