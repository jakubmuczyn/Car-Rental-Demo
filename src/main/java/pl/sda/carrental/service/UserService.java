package pl.sda.carrental.service;

import ch.qos.logback.core.net.server.Client;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.*;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository<User> userRepository;
    private final AdministratorRepository adminRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public UserService(UserRepository<User> userRepository, AdministratorRepository adminRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }
    public List<Administrator> getAllAdmins() {
        return adminRepository.findAll();
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    public void saveEditedEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void saveEditedUser(User user) {
        userRepository.save(user);
    }

    public void saveEditedUser(Administrator administrator) {
        adminRepository.save(administrator);
    }

    public void saveEditedUser(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEditedUser(User user) {
        userRepository.delete(user);
    }

    public <T extends User> Role getPrincipalRole(T user) {
        Set<Role> roles = user.getRoles();
        return (Role) roles.toArray()[0];
    }

}
