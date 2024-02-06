package pl.sda.carrental.model.dto;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.UserService;

import java.util.List;

@Service
public class UserMapper {
    private final UserService userService;
    private final AdministratorRepository administratorRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public UserMapper(UserService userService, AdministratorRepository administratorRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.userService = userService;
        this.administratorRepository = administratorRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public <T extends User> UserDisplayDto getUserDisplayDto(T user){
       return UserDisplayDto.builder()
               .name(user.getName())
               .email(user.getEmail())
               .username(user.getUsername())
               .id(user.getId())
               .principalRole(userService.getPrincipalRole(user).getName())
               .build();
    }

    public <T extends User> List<UserDisplayDto> getUserDisplayDtos(List<T> users) {
        return users.stream().map(this::getUserDisplayDto).toList();
    }

    public Administrator getAdmin(UserDisplayDto userDisplayDto) {
       return administratorRepository.findById(userDisplayDto.getId()).get();
    }
    public Employee getEmployee(UserDisplayDto userDisplayDto) {
        return employeeRepository.findById(userDisplayDto.getId()).get();
    }
    public Customer getCustomer(UserDisplayDto userDisplayDto) {
        return customerRepository.findById(userDisplayDto.getId()).get();
    }
}
