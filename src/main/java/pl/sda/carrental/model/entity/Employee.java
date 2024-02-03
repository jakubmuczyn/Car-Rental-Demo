package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String employeeName;
    
    private String employeeSurname;
    
    @Enumerated(EnumType.STRING)
    private Position position;
    
    @ManyToOne
    private Division division;
    
    public enum Position {
        EMPLOYEE,MANAGER;
    }
}
