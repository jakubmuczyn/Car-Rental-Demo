package pl.sda.carrental.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.carrental.model.dataTransfer.CarDTO;
import pl.sda.carrental.model.dataTransfer.CreateDivisionDTO;
import pl.sda.carrental.service.CarService;

import java.util.List;

@AllArgsConstructor
@Controller
public class CarController {
    
    private final CarService carService;
    
    @GetMapping("/fleet")
    public String getListOfCars(Model model) {
        List<CarDTO> carsDto = carService.getList();
        
        model.addAttribute("cars", carsDto);
        return "fleet/fleetPanel";
    }
    
    @GetMapping("/fleet/{id}")
    public String getCarById(@PathVariable Long id, Model model) {
        CarDTO carDto = carService.getById(id);
        
        model.addAttribute("car", carDto);
        return "fleet/fleetPanelCar";
    }
    
    
    
    
    
    
    @GetMapping("/fleet/add")
    public String addCar(Model model) {
        model.addAttribute("newCar", new CarDTO());
        return "fleet/add";
    }
    @PostMapping("/fleet/add")
    public String addCar(CarDTO newCar) {
        carService.addCar(newCar);
        return "redirect:/fleet";
    }
    
    
    
    
    
}