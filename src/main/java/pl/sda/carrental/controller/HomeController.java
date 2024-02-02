package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.User;
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
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
