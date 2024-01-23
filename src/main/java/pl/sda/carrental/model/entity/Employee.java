package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.sda.carrental.model.enums.Position;

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
