package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final EmployeeRepository employeeRepository;

    public UserController(UserService userService, EmployeeRepository employeeRepository) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/users")
    public String goToUserPanel(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allAdmins", userService.getAllAdmins());
        model.addAttribute("allEmployees", userService.getAllEmployees());
        model.addAttribute("allCustomers", userService.getAllCustomers());
        return "userPanel";
    }

    @GetMapping("/users/employee/{user_id}")
    public String editEmployee(Model model, @PathVariable() long user_id) {
        Employee employee = employeeRepository.findById(user_id).get();
        model.addAttribute("employee", employee);
        model.addAttribute("positions", Employee.Position.values());
        return "employeeEdit";
    }
    @GetMapping("/users/employee/delete/{user_id}")
    public String deleteEmployee(@PathVariable() int user_id) {
        User user = userService.getUserById(user_id);
        userService.deleteEditedUser(user);
        return "home";
    }

    @PostMapping("/users/employee/edit")
    public  String submitEditedUser(Employee employee) {
        userService.saveEditedUser(employee);
        return "home";
    }
}
