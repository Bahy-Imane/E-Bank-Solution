package ebank.solution.repository;

import ebank.solution.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from  transaction where account_id=?",nativeQuery = true)
    List<Transaction> findAccountByAccNumber(Long accNumber);

}
