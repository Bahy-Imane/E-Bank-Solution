package ebank.solution.controller;

import ebank.solution.Dto.LoginDto;
import ebank.solution.Dto.RegisterDto;
import ebank.solution.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AutheticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {
        return authenticationService.login(loginDto, bindingResult);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto registerDto, BindingResult bindingResult) {
        return authenticationService.register(registerDto, bindingResult);
    }
}
