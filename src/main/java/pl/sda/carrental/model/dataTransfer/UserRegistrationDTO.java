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
    
    // @Email(message = "Adres e-mail jest nieprawidłowy")
    private String email;
    
    private Role role;
    
    private String password;
    
    private String repeatPassword;
    
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Adres e-mail jest nieprawidłowy");
        }
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //?: - grupa niezachowująca
        return email.matches(emailRegex);
    }
}
