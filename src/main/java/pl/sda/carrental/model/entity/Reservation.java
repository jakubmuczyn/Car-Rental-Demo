package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Car car;
    
    @ManyToOne
    private Customer customer;
    
    // TODO: Should this be double sided? I don't think division should have a list of reservations..
    @ManyToOne
    private Division rental_division;
    
    @ManyToOne
    private Division return_division;
    
    @Column(nullable = false)
    private LocalDateTime reservation_start;
    
    @Column(nullable = false)
    private LocalDateTime reservation_end;
    
    @Column(nullable = false)
    private BigDecimal cost;

    private Car.RentStatus bookCarStatus;

    public void setCustomer(User user) {
        this.customer = customer;
    }
}
