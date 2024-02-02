package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String brand;
    
    private String model;
    
    private String bodyType;
    
    private Year yearOfProduction;
    
    private String color;
    
    private String mileage;
    
    private RentStatus rentStatus;
    
    private BigDecimal pricePerDay;
    
    public enum RentStatus {
        RENTED,
        AVAILABLE,
        UNAVAILABLE;
    }
}
