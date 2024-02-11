package pl.sda.carrental.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.CarDTO;
import pl.sda.carrental.model.dataTransfer.mappers.CarMapper;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CarService {
    
    private final CarRepository carRepository;
    private final CustomUserDetailsService customUserDetailsService;
    
    public List<CarDTO> getList() {
        final User authenticatedUser = customUserDetailsService.getAuthenticatedUser().orElseThrow(() -> new RuntimeException("Could not acces logged user"));
        List<Car> cars = carRepository.findAll();
        return CarMapper.mapEntityListToDtoList(cars);
    }
    
    public CarDTO getById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            CarDTO dto = CarMapper.map(carOptional.get());
            return dto;
        }
        throw new EntityNotFoundException();
    }
}