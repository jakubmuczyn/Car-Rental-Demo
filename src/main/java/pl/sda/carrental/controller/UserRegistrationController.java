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
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;
import pl.sda.carrental.security.PrincipalRole;
import pl.sda.carrental.service.UserRegistrationService;
import pl.sda.carrental.service.UserService;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    
    private final UserService userService;
    
    private final UserRegistrationService userRegistrationService;
    
    private final RoleRepository roleRepository;
    
    // GET /register
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        model.addAttribute("roles", roleRepository.findAll());
        return "registration";
    }
    
    // POST /register
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO, @RequestParam("roleId") Integer roleId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        Role selectedRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + roleId));
        userDTO.setRole(selectedRole);
        
        if (selectedRole.getRoleName().equals(PrincipalRole.ADMIN.name())) {
            userRegistrationService.registerAdmin(userDTO);
        } else if (selectedRole.getRoleName().equals(PrincipalRole.EMPLOYEE.name())) {
            userRegistrationService.registerEmployee(userDTO);
        } else {
            userRegistrationService.registerCustomer(userDTO);
        }
        
        return "redirect:/login";
    }
}
