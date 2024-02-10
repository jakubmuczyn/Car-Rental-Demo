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
public class Employee extends User {

    //TODO: Why this works with enum but Car doesn't
    @Enumerated(EnumType.STRING)
    private Position position;
    
    @ManyToOne
    private Division division;
    
//    @OneToMany(mappedBy = "employee")
//    private List<CarRental> carRental = new ArrayList<>();

    public enum Position {
        EMPLOYEE,MANAGER
    }

}
