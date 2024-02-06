package pl.sda.carrental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class MakeReservationController {

    private final CustomUserDetailsService customUserDetailsService;
    private final ReservationService reservationService;
    public MakeReservationController(CustomUserDetailsService customUserDetailsService, ReservationService reservationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.reservationService = reservationService;
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
    }
    /*
    @PostMapping("/makeReservation")
    public ResponseEntity<?> makeReservation(@ModelAttribute ReservationDto reservationDto) throws IOException {
        if(reservationService.makeReservation(reservationDto)!= null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

     */

}
