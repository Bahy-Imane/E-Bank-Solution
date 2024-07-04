package ebank.solution.repository;

import ebank.solution.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select * from account where user_user_id = ?", nativeQuery = true)
    List<Account> findAccountByUserUserId (Long userId);
}