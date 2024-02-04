package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.UserService;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting() {
        return "home";
    }

    @GetMapping("/users")
    public String goToUserPanel(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "userPanel";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
