package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.model.repository.ReservationRepository;
import pl.sda.carrental.model.dataTransfer.CarDTO;
import pl.sda.carrental.model.dataTransfer.DivisionDTO;
import pl.sda.carrental.model.dataTransfer.ReservationDTO;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;
import pl.sda.carrental.service.CustomUserDetailsService;
import pl.sda.carrental.service.ReservationService;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;

    public ReservationController(ReservationService reservationService, CustomUserDetailsService customUserDetailsService, ReservationRepository reservationRepository, CarRepository carRepository) {
          this.reservationService = reservationService;
          this.customUserDetailsService = customUserDetailsService;
          this.reservationRepository = reservationRepository;
          this.carRepository = carRepository;
    }

    @GetMapping("/reservation")
    public String reservation(Model model){
        List<CarDTO> cars = reservationService.getListOfCars();
        model.addAttribute("carList", cars);
        return "reservation/reservation";
    }
    @GetMapping("/makeReservation/{id}")
    public String makeReservation(@PathVariable Long id, Model model){
        CarDTO car = reservationService.getCarById(id);
        List<DivisionDTO> divisions = reservationService.getListOfDivision();
        model.addAttribute("car", car);
        model.addAttribute("divisionList", divisions);
        model.addAttribute("reservation", new ReservationDTO());
        return "reservation/makeReservation";
    }
    @PostMapping("/summary/{id}")
    public String summaryRedirect(@ModelAttribute ReservationDTO reservationDto, @PathVariable Long id, Model model){
        CarDTO car = reservationService.getCarById(id);
        reservationService.makeReservation(reservationDto, customUserDetailsService.getAuthenticatedUser().get(), car);
        ReservationDTO reservation = reservationService.getReservationById(carRepository.getById(id).getReservation().getId());
        model.addAttribute("reservation", reservation);
        return "reservation/summary";
    }
    @GetMapping("/summary/{id}")
    public String Summary(@PathVariable Long id){
        return "summary";
    }
    @GetMapping("/myReservations")
    public String myReservations(Model model){
        List<Reservation> reservations = reservationRepository.findAllByCustomer_Id(customUserDetailsService.getAuthenticatedUser().get().getId());
        System.out.println(reservations);
        model.addAttribute("reservationsList", reservations);
        return "reservation/myReservations";
    }
}
