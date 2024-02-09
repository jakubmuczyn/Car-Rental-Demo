package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String greeting() {
        return "home";
    }

    @GetMapping("/oops")
    public String notImplementedYet() {
       return "toBeImplemented";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
