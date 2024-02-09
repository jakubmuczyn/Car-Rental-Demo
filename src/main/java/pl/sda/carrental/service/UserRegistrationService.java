package pl.sda.carrental.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.UserRegistrationDTO;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

@AllArgsConstructor
@Service
public class UserRegistrationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void registerAdmin(UserRegistrationDTO userRegistrationDto) {
        Administrator admin = new Administrator();
        admin.setUsername(userRegistrationDto.getUsername());
        admin.setName(userRegistrationDto.getFirstName() + " " + userRegistrationDto.getLastName());
        admin.setEmail(userRegistrationDto.getEmail());
        admin.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        admin.setRole(userRegistrationDto.getRole());
        userRepository.save(admin);
    }
    
    public void registerManager(UserRegistrationDTO userRegistrationDto) {
        Employee manager = new Employee();
        manager.setUsername(userRegistrationDto.getUsername());
        manager.setName(userRegistrationDto.getFirstName() + " " + userRegistrationDto.getLastName());
        manager.setEmail(userRegistrationDto.getEmail());
        manager.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        manager.setPosition(Employee.Position.MANAGER);
        manager.setRole(userRegistrationDto.getRole());
        userRepository.save(manager);
    }
    
    public void registerEmployee(UserRegistrationDTO userRegistrationDto) {
        Employee employee = new Employee();
        employee.setUsername(userRegistrationDto.getUsername());
        employee.setName(userRegistrationDto.getFirstName() + " " + userRegistrationDto.getLastName());
        employee.setEmail(userRegistrationDto.getEmail());
        employee.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        employee.setPosition(Employee.Position.EMPLOYEE);
        employee.setRole(userRegistrationDto.getRole());
        userRepository.save(employee);
    }
    
    public void registerCustomer(UserRegistrationDTO userRegistrationDto) {
        Customer customer = new Customer();
        customer.setUsername(userRegistrationDto.getUsername());
        customer.setName(userRegistrationDto.getFirstName() + " " + userRegistrationDto.getLastName());
        customer.setEmail(userRegistrationDto.getEmail());
        customer.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        customer.setRole(userRegistrationDto.getRole());
        customer.setRole(userRegistrationDto.getRole());
        userRepository.save(customer);
    }
    

    

    

}
