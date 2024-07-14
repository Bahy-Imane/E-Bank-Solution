package ebank.solution.service;

import ebank.solution.model.User;
import ebank.solution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }

//    public User updateUser(Long id,User user) {
//       User user1 = userRepository.findById(id).orElseThrow(null);
//        user1.setUserName(user.getUserName());
//        user1.setEmail(user.getEmail());
//        user1.setAddress(user.getAddress());
//        user1.setAccounts(user.getAccounts());
//        return userRepository.save(user1);
//    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        User user = userRepository.findUsersByUserName(userName);
//        if (user != null) {
//            throw new UsernameNotFoundException(user.getUserName());
//        }
//        return org.springframework.security.core.userdetails.User
//                .withUsername(userName)
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUserName(userName);
        if (user != null) {

            var springUser = org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();

            return springUser;
//            throw new UsernameNotFoundException("User not found: " + userName);
        }

            return null;
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUserName())
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
    }

}
