package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.entity.Address;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.Role;
import pl.sda.carrental.model.entity.User;
import pl.sda.carrental.model.repository.AddressRepository;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.RoleRepository;
import pl.sda.carrental.model.repository.UserRepository;

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
    private final AddressRepository addressRepository;
    private final DivisionRepository divisionRepository;

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

        User admin = User.builder().username("admin").email("admin@test.pl").password(passwordEncoder.encode("admin")).build();
        userRepository.save(admin);

        testDatabase();
    }

    private void testDatabase() {
        Address address = Address.builder()
                .address("Testowa 12")
                .state("Łódzkie")
                .city("Łódź")
                .build();
        Division division = Division.builder()
                .address(address)
                .build();

        addressRepository.save(address);
        divisionRepository.save(division);

        System.out.println("Address query: " + addressRepository.findAll().get(0).toString());
        System.out.println("Division query: " + divisionRepository.findAll().get(0).toString());


    }
}