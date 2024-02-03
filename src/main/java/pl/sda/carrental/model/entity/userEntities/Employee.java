package pl.sda.carrental.model.entity.userEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.entity.CarRental;
import pl.sda.carrental.model.entity.Division;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Table(name = "Employees")
public class Employee extends User {

    //TODO: Why this works with enum but Car doesn't
    @Enumerated(EnumType.STRING)
    private Position position;
    
    @ManyToOne
    private Division division;
    
    @OneToMany(mappedBy = "employee")
    private List<CarRental> carRental;

    public enum Position {
        EMPLOYEE,MANAGER;
    }

}
