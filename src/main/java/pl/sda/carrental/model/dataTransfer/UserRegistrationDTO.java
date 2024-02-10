package pl.sda.carrental.model.dataTransfer;

import lombok.Getter;
import lombok.Setter;
import pl.sda.carrental.model.entity.userEntities.Role;

@Getter
@Setter
public class UserRegistrationDTO {
    
    private String username;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private Role role;
    
    private String password;
    
    private String repeatPassword;
}
