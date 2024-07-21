package ebank.solution.service;

import ebank.solution.Dto.LoginDto;
import ebank.solution.Dto.RegisterDto;
import ebank.solution.model.User;
import ebank.solution.repository.UserRepository;
import ebank.solution.springSecurityConfig.JwtToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.HashMap;
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







//    private  String createJwtToken(User user) {
//        Instant now = Instant.now();
//
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer(jwtIssuer)
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(24*3600))
//                .subject(user.getUserName())
//                .build();
//
//        var encoder= new NimbusJwtEncoder(
//                new ImmutableSecret<>(jwtSecretKey.getBytes()));
//
//        var params = JwtEncoderParameters.from(
//                JwsHeader.with(MacAlgorithm.HS256).build(),claims);
//
//        return encoder.encode(params).getTokenValue();
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
