package pl.sda.carrental.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@Setter
@Getter
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
    //@ManyToOne
    private String rental_division;
    
    //@ManyToOne
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

    public void setCustomer(User user) {
        this.customer = customer;
    }
    public boolean isInsurance() {
        return insurance;
    }
    public boolean isGoing_abroad() {
        return going_abroad;
    }
}
