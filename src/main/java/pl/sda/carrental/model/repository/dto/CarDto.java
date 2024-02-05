package pl.sda.carrental.model.repository.dto;

import jakarta.persistence.*;
import lombok.*;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.Reservation;

import java.math.BigDecimal;
import java.time.Year;
@Data
public class CarDto {

    private Long id;

    private Division division;

    private Reservation reservation;

    private String brand;

    private String model;

    private String body_type;

    private Year production_year;

    private String color;

    private int mileage;

    @Enumerated(EnumType.STRING)
    private Car.RentStatus status;

    private BigDecimal cost_per_day;
}
