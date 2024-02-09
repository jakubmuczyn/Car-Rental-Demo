package pl.sda.carrental.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.carrental.model.dataTransfer.CreateUserDTO;
import pl.sda.carrental.service.UserRegistrationService;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    
    private final UserRegistrationService userRegistrationService;
    
    // GET /register/customer
    @GetMapping("/register/customer")
    public String registerCustomer(Model model) {
        model.addAttribute("customer", new CreateUserDTO());
        return "registerCustomer";
    }
    
    // POST /register/customer
    @PostMapping("/register/customer")
    public String registerCustomer(CreateUserDTO createUserDto) {
        userRegistrationService.registerCustomer(createUserDto);
        return "redirect:/login";
    }
    
    // GET /register/employee
    @GetMapping("/register/employee")
    public String registerEmployee(Model model) {
        model.addAttribute("employee", new CreateUserDTO());
        return "registerEmployee";
    }
    
    // POST /register/employee
    @PostMapping("/register/employee")
    public String registerEmployee(CreateUserDTO createUserDto) {
        userRegistrationService.registerEmployee(createUserDto);
        return "redirect:/login";
    }
    
    // GET /register/manager
    @GetMapping("/register/manager")
    public String registerManager(Model model) {
        model.addAttribute("manager", new CreateUserDTO());
        return "registerManager";
    }
    
    // POST /register/manager
    @PostMapping("/register/manager")
    public String registerManager(CreateUserDTO createUserDto) {
        userRegistrationService.registerManager(createUserDto);
        return "redirect:/login";
    }
    
    // GET /register/admin
    @GetMapping("/register/admin")
    public String registerAdmin(Model model) {
        model.addAttribute("admin", new CreateUserDTO());
        return "registerAdmin";
    }
    
    // POST /register/admin
    @PostMapping("/register/admin")
    public String registerAdmin(CreateUserDTO createUserDto) {
        userRegistrationService.registerAdmin(createUserDto);
        return "redirect:/login";
    }
}
