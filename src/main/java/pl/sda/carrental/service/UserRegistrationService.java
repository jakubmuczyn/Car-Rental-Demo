package pl.sda.carrental.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.CreateUserDTO;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

@AllArgsConstructor
@Service
public class UserRegistrationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void registerCustomer(CreateUserDTO createUserDto) {
        Customer customer = new Customer();
        customer.setUsername(createUserDto.getUsername());
        customer.setName(createUserDto.getFirstName() + " " + createUserDto.getLastName());
        customer.setEmail(createUserDto.getEmail());
        customer.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        
        userRepository.save(customer);
    }
    
    public void registerEmployee(CreateUserDTO createUserDto) {
        Employee employee = new Employee();
        employee.setUsername(createUserDto.getUsername());
        employee.setName(createUserDto.getFirstName() + " " + createUserDto.getLastName());
        employee.setEmail(createUserDto.getEmail());
        employee.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        employee.setPosition(Employee.Position.EMPLOYEE);
        
        userRepository.save(employee);
    }
    
    public void registerManager(CreateUserDTO createUserDto) {
        Employee manager = new Employee();
        manager.setUsername(createUserDto.getUsername());
        manager.setName(createUserDto.getFirstName() + " " + createUserDto.getLastName());
        manager.setEmail(createUserDto.getEmail());
        manager.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        manager.setPosition(Employee.Position.MANAGER);
        
        userRepository.save(manager);
    }
    
    public void registerAdmin(CreateUserDTO createUserDto) {
        Administrator admin = new Administrator();
        admin.setUsername(createUserDto.getUsername());
        admin.setName(createUserDto.getFirstName() + " " + createUserDto.getLastName());
        admin.setEmail(createUserDto.getEmail());
        admin.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        
        userRepository.save(admin);
    }
}
