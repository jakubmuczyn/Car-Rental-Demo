package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Divisions")
public class Division {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Address address;
    
    // @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;
    
    @OneToMany(mappedBy = "Division")
    // @ToString.Exclude
    private List<Car> cars;


    public void addEmployee(Employee employee) {
        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
    }

    public void addCar(Car car) {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        this.cars.add(car);
        car.setDivision(this);
    }
}
