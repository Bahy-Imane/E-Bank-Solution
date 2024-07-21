package ebank.solution.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @NotEmpty(message = "Username must not be empty")
    private String userName;

    @NotEmpty(message = "Password must not be empty")
    private String password;
}
