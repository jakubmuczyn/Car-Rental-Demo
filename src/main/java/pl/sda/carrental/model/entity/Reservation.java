package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.carrental.model.entity.userEntities.Customer;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Car car;
    
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Customer customer;

    private String rental_division;

    private String return_division;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date reservation_start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date reservation_end;
    
    @Column(nullable = false)
    private BigDecimal cost;
    
    @Column(nullable = false)
    private boolean insurance;
    
    @Column(nullable = false)
    private boolean going_abroad;

    public boolean isInsurance() {
        return insurance;
    }
    public boolean isGoing_abroad() {
        return going_abroad;
    }
}
