package pl.sda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.service.ReservationService;
import java.util.List;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("/home")
    public String reservation(Model model,  @RequestParam(value="carId", required = false) Long carId){
        List<CarDto> cars = reservationService.getList();
        model.addAttribute("carList", cars);
        model.addAttribute("carId", carId);
        return "reservation";
    }
}
