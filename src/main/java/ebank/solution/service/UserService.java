package ebank.solution.service;

import ebank.solution.model.User;
import ebank.solution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }

}
