package pl.sda.carrental.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.carrental.model.dataTransfer.UserRegistrationDTO;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;
import pl.sda.carrental.service.UserRegistrationService;
import pl.sda.carrental.service.UserService;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    
    @Autowired
    private final UserService userService;
    
    @Autowired
    private final UserRegistrationService userRegistrationService;
    
    @Autowired
    private final RoleRepository roleRepository;
    
    
    // GET /register/employee
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        model.addAttribute("roles", roleRepository.findAll());
        return "registration";
    }
    
    
    
    
    
    
    // POST /register/employee
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO, BindingResult bindingResult, @RequestParam("roleId") Integer roleId) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        Role selectedRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + roleId));
        userDTO.setRole(selectedRole);
        userRegistrationService.register(userDTO);
        
        
        
        
        if (selectedRole.getRoleName().equals("Customer")) {
            userRegistrationService.registerCustomer(user);
        } else if (selectedRole.getRoleName().equals("Employee")) {
            userRegistrationService.registerEmployee(user);
        } else if (selectedRole.getRoleName().equals("Administrator")) {
            userRegistrationService.registerAdmin(user);
        } else {
            // Obsługa innych ról, jeśli jest to konieczne
        }
        
        
        
        
        
        
        
        return "redirect:/login";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // GET /register/customer
    @GetMapping("/register/customer")
    public String registerCustomer(Model model) {
        model.addAttribute("customer", new UserRegistrationDTO());
        return "registerCustomer";
    }
    
    // POST /register/customer
    @PostMapping("/register/customer")
    public String registerCustomer(UserRegistrationDTO userRegistrationDto) {
        userRegistrationService.registerCustomer(userRegistrationDto);
        return "redirect:/login";
    }
    
    // GET /register/manager
    @GetMapping("/register/manager")
    public String registerManager(Model model) {
        model.addAttribute("manager", new UserRegistrationDTO());
        return "registerManager";
    }
    
    // POST /register/manager
    @PostMapping("/register/manager")
    public String registerManager(UserRegistrationDTO userRegistrationDto) {
        userRegistrationService.registerManager(userRegistrationDto);
        return "redirect:/login";
    }
    
    // GET /register/admin
    @GetMapping("/register/admin")
    public String registerAdmin(Model model) {
        model.addAttribute("admin", new UserRegistrationDTO());
        return "registerAdmin";
    }
    
    // POST /register/admin
    @PostMapping("/register/admin")
    public String registerAdmin(UserRegistrationDTO userRegistrationDto) {
        userRegistrationService.registerAdmin(userRegistrationDto);
        return "redirect:/login";
    }
}
