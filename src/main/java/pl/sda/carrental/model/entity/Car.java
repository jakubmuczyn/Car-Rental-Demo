package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Year;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Reservation reservation;

    private String brand;
    private String model;
    private String body_type;
    private Year production_year;
    private String color;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private RentStatus status;
    private BigDecimal cost_per_day;

    public enum RentStatus {
        RENTED, AVAILABLE, UNAVAILABLE
    }
    public void addReservationId(Reservation reservation) {
        this.reservation = reservation;
    }
}
