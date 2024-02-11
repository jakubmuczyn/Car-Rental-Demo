package pl.sda.carrental.service;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.CreateDivisionDTO;
import pl.sda.carrental.model.dataTransfer.mappers.EmployeeMapper;
import pl.sda.carrental.model.entity.Address;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.AddressRepository;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisionService {
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public DivisionService(DivisionRepository divisionRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.divisionRepository = divisionRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public void addEmployees(Division division, Employee employee) {
        division.addEmployee(employee);
        divisionRepository.save(division);
    }

    public void addEmployees(Division division, List<Employee> employees) {
        employees.forEach(e -> addEmployees(division, e));
    }

    public void removeEmployee(Division division, Employee employee) {
        division.getEmployees().remove(employee);
        employee.setDivision(null);
        divisionRepository.save(division);
    }

    public void createDivision(CreateDivisionDTO newDivision) {
        Address address = Address.builder()
                .address(newDivision.getAddress())
                .city(newDivision.getCity())
                .state(newDivision.getState()).build();
        Employee employee = employeeRepository.findById(newDivision.getManager().getId()).get();

        addressRepository.save(address);

        Division division = Division.builder()
                .address(address)
                .manager(employee)
                .build();

        divisionRepository.save(division);
        division.addEmployee(employee);
        divisionRepository.save(division);
    }
}
