package ebank.solution.repository;
import ebank.solution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
 public interface UserRepository extends JpaRepository<User, Long>{
   public User findUsersByUserName(String userName);
   public User findUsersByEmail(String email);
}

