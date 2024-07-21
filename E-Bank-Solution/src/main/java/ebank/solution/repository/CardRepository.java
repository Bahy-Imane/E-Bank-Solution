package ebank.solution.repository;

import ebank.solution.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "select * from card where account_id=?", nativeQuery = true)
    public List<Card> findCardsByAccount_AccountId(Long accountId);


}
