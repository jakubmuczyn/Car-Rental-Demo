package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sda.carrental.model.entity.userEntities.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Divisions")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long division_id;

    @OneToOne
    private Address address;
    @ToString.Exclude
    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();
    @OneToMany(mappedBy = "division")
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();


    public void addEmployee(Employee employee) {
        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
        employee.setDivision(this);
    }

    public void addCar(Car car) {
        if (this.cars == null)
            this.cars = new ArrayList<>();

        this.cars.add(car);
        car.setDivision(this);
    }
}
