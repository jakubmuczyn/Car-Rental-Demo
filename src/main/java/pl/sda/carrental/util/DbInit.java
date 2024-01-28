package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.entity.*;
import pl.sda.carrental.model.enums.Position;
import pl.sda.carrental.model.repository.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class DbInit {
    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;


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

        Administrator admin = Administrator.builder().name("admin").username("admin").email("admin@test.pl").password(passwordEncoder.encode("admin")).roles(adminRoles).build();
        administratorRepository.save(admin);

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

        Employee employee = Employee.builder()
                .division(division)
                .name("Jan Kowalski")
                .position(Position.EMPLOYEE)
                .email("jan.kowalski@company.com")
                .password(passwordEncoder.encode("pracownik"))
                .username("pracownik")
                .build();

        Client client = Client.builder()
                .name("Maciej Konsument")
                .email("maciej.konsument@gmail.com")
                .password(passwordEncoder.encode("klient"))
                .username("klient")
                .build();


        division.addEmployee(employee);

        addressRepository.save(address);
        divisionRepository.save(division);
        employeeRepository.save(employee);
        clientRepository.save(client);

        System.out.println("Address query: " + addressRepository.findAll().get(0).toString());
        System.out.println("Division query: " + divisionRepository.findAll().get(0).toString());
        System.out.println("Employee query: " + employeeRepository.findAll().get(0).toString());
        System.out.println("Client query: " + clientRepository.findAll().get(0).toString());


    }
}