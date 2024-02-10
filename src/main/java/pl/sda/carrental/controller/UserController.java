package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.dataTransfer.AdminDTO;
import pl.sda.carrental.model.dataTransfer.CustomerDTO;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.dataTransfer.mappers.AdministratorMapper;
import pl.sda.carrental.model.dataTransfer.mappers.CustomerMapper;
import pl.sda.carrental.model.dataTransfer.mappers.EmployeeMapper;
import pl.sda.carrental.model.dataTransfer.mappers.UserMapper;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final AdministratorRepository administratorRepository;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final EmployeeMapper employeeMapper;
    private final AdministratorMapper administratorMapper;

    public UserController(UserService userService, EmployeeRepository employeeRepository, CustomerRepository customerRepository, AdministratorRepository administratorRepository, UserMapper userMapper, CustomerMapper customerMapper, EmployeeMapper employeeMapper, AdministratorMapper administratorMapper) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.administratorRepository = administratorRepository;
        this.userMapper = userMapper;
        this.customerMapper = customerMapper;
        this.employeeMapper = employeeMapper;
        this.administratorMapper = administratorMapper;
    }
    @GetMapping("/users")
    public String goToUserPanel(Model model) {
        model.addAttribute("users", userMapper.getUserDisplayDtos(userService.getAllUsers()));
        return "userPanels/userPanel";
    }
    @GetMapping("/users/user/toggle/{user_id}")
    public String toggleUserActive(@PathVariable() Long user_id) {
        userService.toggle(user_id);
        return "redirect:/users";
    }
    @GetMapping("/users/administrator/{user_id}")
    public String editAdministrator(Model model, @PathVariable() long user_id) {
        Administrator administrator = administratorRepository.findById(user_id).get();
        AdminDTO administratorDTO = administratorMapper.getDto(administrator);
        model.addAttribute("administrator", administratorDTO);
        return "userPanels/administratorEdit";
    }
    @PostMapping("/users/administrator/save")
    public  String submitEditedUser(AdminDTO adminDTO) {
        userService.saveUser(administratorMapper.getUserClass(adminDTO));
        return "redirect:/users";
    }
    @GetMapping("/users/customer/{user_id}")
    public String editCustomer(Model model, @PathVariable() long user_id) {
        Customer customer = customerRepository.findById(user_id).get();
        CustomerDTO customerDTO = customerMapper.getDto(customer);
        model.addAttribute("customer", customerDTO);
        return "userPanels/customerEdit";
    }
    @PostMapping("/users/customer/save")
    public  String submitEditedUser(CustomerDTO customerDTO) {
        userService.saveUser(customerMapper.getUserClass(customerDTO));
        return "redirect:/users";
    }
    @GetMapping("/users/employee/{user_id}")
    public String editEmployee(Model model, @PathVariable() long user_id) {
        Employee employee = employeeRepository.findById(user_id).get();
        EmployeeDTO employeeDto = employeeMapper.getDto(employee);
        model.addAttribute("employee", employeeDto);
        model.addAttribute("positions", Employee.Position.values());
        return "userPanels/employeeEdit";
    }
    @PostMapping("/users/employee/saves")
    public  String submitEditedEmployee(EmployeeDTO employee) {
        userService.saveUser(employeeMapper.getUserClass(employee));
        return "redirect:/users";
    }
}
