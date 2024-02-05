package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;

import java.util.List;

@Controller
public class HomeController {
    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationService reservationService;
    public HomeController(CustomUserDetailsService customUserDetailsService, ReservationService reservationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        List<CarDto> cars = reservationService.getList();
        User user = customUserDetailsService
            .getAuthenticatedUser()
            .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("carList", cars);
        model.addAttribute("currentUser", user);
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
