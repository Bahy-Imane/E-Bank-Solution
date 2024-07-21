package ebank.solution.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterDto {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    @Size(min = 6,message = "Minimum Password Length is 6 Characters")
    private String password;


}
