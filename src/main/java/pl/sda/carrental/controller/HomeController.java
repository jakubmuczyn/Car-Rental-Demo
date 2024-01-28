package pl.sda.carrental.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.User;
import pl.sda.carrental.model.repository.UserRepository;
import pl.sda.carrental.service.UserService;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        User user = userService
            .getAuthenticatedUser()
            .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("currentUser", user);
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }
    
    @GetMapping("/employee")
    public String employee() {
        return "employee";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
