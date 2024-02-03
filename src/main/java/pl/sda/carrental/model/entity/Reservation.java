package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDate reservationDate;
    
    private Customer customer;
    
    private Car car;
    
    private LocalDateTime reservationDateFrom;
    
    private LocalDateTime reservationDateTo;
    
    private Division divisionOfRent;
    
    private Division divisionOfReturn;
    
    private BigDecimal cost;
}
