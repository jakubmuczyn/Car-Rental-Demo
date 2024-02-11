package pl.sda.carrental.model.dataTransfer;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.carrental.model.entity.Car;

import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTOForPanel {
    
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Year productionYear;
    private String color;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private Car.RentStatus status;
}
