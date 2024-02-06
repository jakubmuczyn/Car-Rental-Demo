package pl.sda.carrental.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.repository.dto.DivisionDto;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;

import java.util.List;

@Controller
public class SummaryController {
    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationService reservationService;
    public SummaryController(CustomUserDetailsService customUserDetailsService, ReservationService reservationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.reservationService = reservationService;
    }

    @GetMapping("/summary")
    public void Summary(Model model){
        List<CarDto> cars = reservationService.getListOfCars();
        List<DivisionDto> divisions = reservationService.getListOfDivision();
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("carList", cars);
        model.addAttribute("divisionList", divisions);
        model.addAttribute("currentUser", user);
    }
}
