package pl.sda.carrental.service;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;

import java.util.List;

@Service
public class DivisionService {
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;

    public DivisionService(DivisionRepository divisionRepository, EmployeeRepository employeeRepository) {
        this.divisionRepository = divisionRepository;
        this.employeeRepository = employeeRepository;
    }

    public void addEmployees(Division division, Employee employee) {
        division.addEmployee(employee);
        divisionRepository.save(division);
    }

    public void addEmployees(Division division, List<Employee> employees) {
        employees.forEach(e -> addEmployees(division, e));
    }

}
