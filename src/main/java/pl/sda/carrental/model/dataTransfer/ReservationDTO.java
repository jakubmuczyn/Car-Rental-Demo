package pl.sda.carrental.model.dataTransfer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.userEntities.Customer;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Data
public class ReservationDTO {
    private Long id;
    private Car car;
    private Customer customer;
    private String rental_division;
    private String return_division;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservation_start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservation_end;
    private BigDecimal cost;
    private boolean insurance;
    private boolean going_abroad;

    public boolean isInsurance() {
        return insurance;
    }
    public boolean isGoing_abroad() {
        return going_abroad;
    }

}
