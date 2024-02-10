package pl.sda.carrental.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.ToString;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.model.repository.ReservationRepository;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.repository.dto.DivisionDto;
import pl.sda.carrental.model.repository.dto.ReservationDto;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;
import java.io.IOException;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ReservationController(ReservationService reservationService, CustomUserDetailsService customUserDetailsService, ReservationRepository reservationRepository, CarRepository carRepository, UserRepository userRepository) {
          this.reservationService = reservationService;
          this.customUserDetailsService = customUserDetailsService;
          this.reservationRepository = reservationRepository;
          this.carRepository = carRepository;
          this.userRepository = userRepository;
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
    public String summaryRedirect(@ModelAttribute ReservationDto reservationDto, @PathVariable Long id, HttpServletResponse response, Model model) throws IOException{
        CarDto car = reservationService.getCarById(id);
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        reservationService.makeReservation(reservationDto, user, car);
        model.addAttribute("currentUser", user);
        ReservationDto reservation = reservationService.getReservationById(carRepository.getById(id).getReservation().getId());
        model.addAttribute("reservation", reservation);
        //response.sendRedirect("/summary");
        return "summary";
    }
    @GetMapping("/summary/{id}")
    public String Summary(@PathVariable Long id, Model model){
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        model.addAttribute("currentUser", user);
        return "summary";
    }
    @GetMapping("/myReservations")
    public void myReservations(Model model){
        User user = customUserDetailsService
                .getAuthenticatedUser()
                .orElseThrow(() -> new UsernameNotFoundException("Logged in user not found."));
        List<Reservation> reservations = reservationRepository.findAllByCustomer_Id(user.getId());
        System.out.println(reservations);
        model.addAttribute("currentUser", user);
        model.addAttribute("reservationsList", reservations);
    }
}
