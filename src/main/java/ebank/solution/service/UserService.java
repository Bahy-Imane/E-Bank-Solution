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

    public User updateUser(Long id,User user) {
       User user1 = userRepository.findById(id).orElseThrow(null);
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setAccounts(user.getAccounts());
        return userRepository.save(user1);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



}
