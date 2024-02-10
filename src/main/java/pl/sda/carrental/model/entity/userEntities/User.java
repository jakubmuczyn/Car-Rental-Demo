package pl.sda.carrental.model.entity.userEntities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.entity.Reservation;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @NotNull
    private Role role;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private boolean isActive = true;



    public void addReservation(Reservation reservation){}
    public List<Reservation> getCustomerReservations(){
        return getCustomerReservations();
    }
    public Long getReservationIdFromUser(){
        return getReservationIdFromUser();
    }
}
