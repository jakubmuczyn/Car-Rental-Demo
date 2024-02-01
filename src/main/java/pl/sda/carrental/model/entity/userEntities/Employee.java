package pl.sda.carrental.model.entity.userEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.enums.Position;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Table(name = "Employees")
public class Employee extends User {

    private Position position;
    @ManyToOne
    private Division division;

}
