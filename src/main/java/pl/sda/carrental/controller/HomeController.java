package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String greeting() {
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
