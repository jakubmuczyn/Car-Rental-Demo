package pl.sda.carrental.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.entity.CarMapper;
import pl.sda.carrental.model.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final CarRepository carRepository;
    public List<CarDto> getList(){
        List<Car> cars = carRepository.findAll();
        return CarMapper.mapEntityListToDtoList(cars);
    }
    public CarDto getById(Long id) {
        Optional<Car> todoOptional = carRepository.findById(id);
        if (todoOptional.isPresent()) {
            CarDto dto = CarMapper.map(todoOptional.get());
            return dto;
        }
        throw new EntityNotFoundException();
    }
}
