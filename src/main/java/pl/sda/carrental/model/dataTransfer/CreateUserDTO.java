package pl.sda.carrental.model.dataTransfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    
    private String username;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private String repeatPassword;
}
