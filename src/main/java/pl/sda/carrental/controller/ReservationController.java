package pl.sda.carrental.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.NamedStoredProcedureQueries;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.carrental.model.entity.NewCarId;
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
        model.addAttribute("newCarId", new NewCarId());
    }
    @PostMapping("/makeReseravtion")
    public @ResponseBody void reservationRedirect(@ModelAttribute NewCarId newCarId, Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("newCarId", newCarId.getId());
        try {
            response.sendRedirect("/makeReservation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/makeReservation")
    public void makeReservation(Model model){
        List<CarDto> cars = reservationService.getListOfCars();
        List<DivisionDto> divisions = reservationService.getListOfDivision();
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("carList", cars);
        model.addAttribute("divisionList", divisions);
        model.addAttribute("currentUser", user);
        model.addAttribute("reservation", new ReservationDto());
        model.addAttribute("flag1", false);
        model.addAttribute("flag2", false);
    }
    @PostMapping("/summary")
    public @ResponseBody void summaryRedirect(@ModelAttribute ReservationDto reservationDto, Model model, final RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        model.addAttribute("reservation", reservationDto);
        reservationService.makeReservation(reservationDto);
        try {
            redirectAttributes.addFlashAttribute("message", "Rezerwacja pomyślnie złożona!");
            response.sendRedirect("/summary");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/summary")
    public void Summary(Model model){
        List<CarDto> cars = reservationService.getListOfCars();
        List<DivisionDto> divisions = reservationService.getListOfDivision();
        List<ReservationDto> reservations = reservationService.getListOfReservations();
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("carList", cars);
        model.addAttribute("divisionList", divisions);
        model.addAttribute("reservationList", reservations);
        model.addAttribute("currentUser", user);
    }
}
