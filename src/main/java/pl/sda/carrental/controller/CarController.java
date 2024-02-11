package pl.sda.carrental.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.carrental.model.dataTransfer.CarDTO;
import pl.sda.carrental.model.dataTransfer.mappers.CarMapper;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.service.CarService;

import java.util.List;

@AllArgsConstructor
@Controller
public class CarController {
    
    private final CarService carService;
    // private final CarRepository carRepository;
    // private final CarMapper carMapper;
    // private final CarDTO carDTO;
    
    // @GetMapping("/fleet")
    // public String getCars(Model model) {
    //     List<Car> cars = carRepository.findAll();
    //     List<CarDTO> carsDTO = carMapper
    //     model.addAttribute("cars", carsDTO);
    //     return "fleet/fleetPanel";
    // }
    
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
}