package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.carrental.entity.Role;
import pl.sda.carrental.entity.User;
import pl.sda.carrental.repository.RoleRepository;
import pl.sda.carrental.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        User admin = new User("admin", "admin", roles);
        userRepository.save(admin);
    }
}
