package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.dto.UserMapper;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.UserService;

import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final AdministratorRepository administratorRepository;
    private final UserMapper userMapper;

    public UserController(UserService userService, EmployeeRepository employeeRepository, CustomerRepository customerRepository, AdministratorRepository administratorRepository, UserMapper userMapper) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.administratorRepository = administratorRepository;
        this.userMapper = userMapper;
    }
    @GetMapping("/users")
    public String goToUserPanel(Model model) {
        model.addAttribute("users", userMapper.getUserDisplayDtos(userService.getAllUsers()));
        return "userPanel";
    }

    @GetMapping("/users/employee/{user_id}")
    public String editEmployee(Model model, @PathVariable() long user_id) {
        Employee employee = employeeRepository.findById(user_id).get();
        model.addAttribute("employee", employee);
        model.addAttribute("positions", Employee.Position.values());
        model.addAttribute("roles", employee.getRoles().stream().map(role -> String.valueOf(role.getId())).collect(Collectors.joining(";")));

        return "employeeEdit";
    }
    @GetMapping("/users/user/delete/{user_id}")
    public String deleteEmployee(@PathVariable() Long user_id) {
        userService.deactivate(user_id);
        return "home";
    }
    @GetMapping("/users/customer/{user_id}")
    public String editCustomer(Model model, @PathVariable() long user_id) {
        Customer customer = customerRepository.findById(user_id).get();
        model.addAttribute("customer", customer);
        return "customerEdit";
    }

    @PostMapping("/users/customer/save")
    public  String submitEditedUser(Customer customer) {
        userService.saveUser(customer);
        return "home";
    }
    @PostMapping("/users/employee/save")
    public  String submitEditedEmployee(Employee employee) {
        userService.saveUser(employee);

        return "home";
    }
}
