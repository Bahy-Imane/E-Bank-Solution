package ebank.solution.repository;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{}

