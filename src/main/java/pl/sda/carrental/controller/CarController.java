package pl.sda.carrental.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.sda.carrental.service.CarService;

@AllArgsConstructor
@Controller
public class CarController {
    
    private final CarService carService;
}