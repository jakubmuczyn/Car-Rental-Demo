package pl.sda.carrental.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.repository.dto.DivisionDto;
import pl.sda.carrental.model.repository.dto.ReservationDto;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;
import java.io.IOException;
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
    }
    @GetMapping("/makeReservation/{id}")
    public String makeReservation(@PathVariable Long id, Model model){
        CarDto car = reservationService.getCarById(id);
        List<DivisionDto> divisions = reservationService.getListOfDivision();
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("car", car);
        model.addAttribute("divisionList", divisions);
        model.addAttribute("currentUser", user);
        model.addAttribute("reservation", new ReservationDto());
        return "makeReservation";
    }
    @PostMapping("/summary/{id}")
    public @ResponseBody void summaryRedirect(@ModelAttribute ReservationDto reservationDto, @PathVariable Long id, HttpServletResponse response) throws IOException{
        CarDto car = reservationService.getCarById(id);
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        reservationService.makeReservation(reservationDto, user, car);
        response.sendRedirect("/summary");
    }
    @GetMapping("/summary")
    public void Summary(Model model){
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        ReservationDto reservation = reservationService.getReservationById(user.getReservationIdFromUser());
        model.addAttribute("reservation", reservation);
        model.addAttribute("currentUser", user);
    }
    @GetMapping("/myReservations")
    public void myReservations(Model model){
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        List<ReservationDto> reservationsList = reservationService.getListOfReservations();
        model.addAttribute("reservationsList", reservationsList);
        model.addAttribute("currentUser", user);
    }
}
