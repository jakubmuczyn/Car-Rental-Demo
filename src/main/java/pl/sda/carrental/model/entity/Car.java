package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.entity.enums.RentStatus;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    private String brand;
    private String model;
    private String body_type;
    private Year production_year;
    private String color;
    private int mileage;
    private RentStatus status;
    private BigDecimal cost_per_day;

}
