package pl.sda.carrental.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Administrators")
public class Administrator extends User {
//    private String username;
//    private String email;
//    private String password;
//    private Set<Role> roles;
//    @Builder
//    public Administrator(String name, String username, String email, String password, Set<Role> roles) {
//        super(name, username, email, password, roles);
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//    }
//    @Builder
//    public Administrator(User user) {
//        super(user.getName(), user.getUsername(), user.getEmail(), password, roles);
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//    }

}
