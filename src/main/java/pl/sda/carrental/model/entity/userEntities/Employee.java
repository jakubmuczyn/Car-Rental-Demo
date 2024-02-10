package pl.sda.carrental.model.entity.userEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.entity.Division;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Table(name = "Employees")
public class Employee extends User {

    @Enumerated(EnumType.STRING)
    private Position position;
    
    @ManyToOne
    private Division division;

//    @OneToMany(mappedBy = "employee")
//    private List<CarRental> carRental = new ArrayList<>();

    public enum Position {
        EMPLOYEE,
        MANAGER
    }
}