package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.entity.Role;
import pl.sda.carrental.entity.User;
import pl.sda.carrental.repository.RoleRepository;
import pl.sda.carrental.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class DbInit {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @PostConstruct
    private void postConstruct() {
        Role adminRole = Role.builder().name("Admin").build();
        Role employeeRole = Role.builder().name("Employee").build();
        Role customerRole = Role.builder().name("Customer").build();
        roleRepository.save(adminRole);
        roleRepository.save(employeeRole);
        roleRepository.save(customerRole);
        
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        
        User admin = User.builder()
                .username("admin")
                .email("admin@test.pl")
                .password(passwordEncoder.encode("admin"))
                .build();
        userRepository.save(admin);
    }
}