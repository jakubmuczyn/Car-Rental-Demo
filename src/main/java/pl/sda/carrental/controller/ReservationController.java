package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.entity.NewCarId;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;
import java.util.List;
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomUserDetailsService customUserDetailsService;

    public ReservationController(ReservationService reservationService, CustomUserDetailsService customUserDetailsService) {
          this.reservationService = reservationService;
          this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/reservation")
    public void reservation(Model model){
        List<CarDto> cars = reservationService.getListOfCars();
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("carList", cars);
        model.addAttribute("currentUser", user);
        model.addAttribute("newCarId", new NewCarId());
    }
    @PostMapping("/reservation")
    public String reservationSubmit(@ModelAttribute NewCarId newCarId, Model model){
        model.addAttribute("newCarId", newCarId);
        return "redirect:/makeReservation";
    }
}
