package pl.sda.carrental.model.repository.dto;

import lombok.Data;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Customer;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private Long id;
    private Car car;
    private Customer customer;
    private Division rental_division;
    private Division return_division;
    private LocalDateTime reservation_start;
    private LocalDateTime reservation_end;
    private BigDecimal cost;
    private Car.RentStatus bookCarStatus;
}
