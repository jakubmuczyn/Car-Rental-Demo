package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.service.CustomUserDetailsService;

@Controller
public class HomeController {
    private final CustomUserDetailsService customUserDetailsService;

    public HomeController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        User user = customUserDetailsService
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
