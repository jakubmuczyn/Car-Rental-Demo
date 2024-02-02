package pl.sda.carrental.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.sda.carrental.model.entity.Address;
import pl.sda.carrental.model.entity.User;

@Getter
@Setter
public class CreateUserDto {
    
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Address address;
    private String password;
    private User.Role role;
}
