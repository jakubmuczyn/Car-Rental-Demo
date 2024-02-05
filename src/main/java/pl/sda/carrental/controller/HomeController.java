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

    @GetMapping("/")
    public String greeting() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
