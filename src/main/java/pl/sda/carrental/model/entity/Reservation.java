package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date reservationDate;
    
    private Customer customer;
    
    private Car car;
    
    private LocalDateTime reservationDateFrom;
    
    private LocalDateTime reservationDateTo;
    
    private Division divisionOfRent;
    
    private Division divisionOfReturn;
    
    private BigDecimal cost;
}
