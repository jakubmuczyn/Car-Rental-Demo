package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sda.carrental.model.entity.userEntities.Employee;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Car_rentals")
public class CarRental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Reservation reservation;
    
    @ManyToOne
    private Employee employee;
    
    private LocalDate rentalDate;
    
    private LocalDate returnDate;
    
    private String comment;
    
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
    
    public enum RentalStatus {
        ONGOING,
        CLOSED
    }
}
