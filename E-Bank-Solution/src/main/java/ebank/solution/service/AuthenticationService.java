package ebank.solution.service;

import ebank.solution.Dto.LoginDto;
import ebank.solution.Dto.RegisterDto;
import ebank.solution.model.User;
import ebank.solution.repository.UserRepository;
import ebank.solution.springSecurityConfig.JwtToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtT;



//    @GetMapping("/profile")
//    public ResponseEntity<Object> profile(Authentication auth) {
//        var response = new HashMap<String, Object>();
//        response.put("userName",auth);
//        response.put("Authorities",auth);
//
//        var user = userRepository.findUsersByUserName(String.valueOf(auth));
//        response.put("user", user);
//
//        return ResponseEntity.ok(response);
//    }


    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorsList = bindingResult.getAllErrors();
            var errorsMap = new HashMap<String, String>();


            for (int i = 0; i < errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        var bCryptEncoder =new BCryptPasswordEncoder();
        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setAddress(registerDto.getAddress());
        user.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

        try {
                var otherUser = userRepository.findUsersByUserName(registerDto.getUserName());
                if (otherUser != null){
                    return ResponseEntity.badRequest().body("Username already exists");
        }
                otherUser = userRepository.findUsersByEmail(registerDto.getEmail());
                if (otherUser != null){
                    return ResponseEntity.badRequest().body("Email already exists");
                }
                userRepository.save(user);


                String jwtToken = jwtT.createJwtToken(user);
                var response = new HashMap<String, Object>();
                response.put("token", jwtToken);
                response.put("user",user);
                return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            System.out.println("There is a problem occured while saving user");
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Error");
    }




    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorsList = bindingResult.getAllErrors();
            var errorsMap = new HashMap<String, String>();


            for (int i = 0; i < errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));

            User user = userRepository.findUsersByUserName(loginDto.getUserName());
            String jwtToken = jwtT.createJwtToken(user);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user",user);
            return ResponseEntity.ok(response);

        }
        catch (Exception e) {
            System.out.println("There is a problem occured while Loging user");
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Bad username or password");

    }
}
