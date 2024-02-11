package pl.sda.carrental.service;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

@Service
public class EmployeeService extends UserService {
    public EmployeeService(UserRepository<User> userRepository, AdministratorRepository adminRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository1, AdministratorRepository administratorRepository, UserRepository userRepository1, DivisionService divisionService) {
        super(userRepository, adminRepository, employeeRepository, customerRepository, employeeRepository1, administratorRepository, userRepository1, divisionService);
    }
}
