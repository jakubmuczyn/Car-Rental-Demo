package pl.sda.carrental;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.DivisionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DivisionTests {
    @MockBean
    DivisionRepository divisionRepository;
    @MockBean
    EmployeeRepository employeeRepository;
    Employee testEmployee;
    Employee testEmployee2;
    Employee testEmployee3;
    Division testDivision;
    @Autowired
    DivisionService divisionService;


    @BeforeEach
    void createTestEmployee() {
        this.testEmployee = Employee.builder().id(1L).name("Andrzej Pracuś").email("andrzej.pracus@gmail.com").build();
        this.testEmployee2 = Employee.builder().id(2L).name("Michał Robotnik").email("michal.robotnik@gmail.com").build();
        this.testEmployee3 = Employee.builder().id(3L).name("Michał Robotnik").email("michal.robotnik@gmail.com").build();
        List<Employee> employees = new ArrayList<>();
        employees.add(testEmployee);

        this.testDivision = Division.builder().employees(employees).build();
    }

    @Test
    void addEmployeeTest() {
        List<Division> mockDivisions = new ArrayList<>();
        mockDivisions.add(testDivision);
        Mockito.when(divisionRepository.findAll()).thenReturn(mockDivisions);

        List<Division> divisions = divisionRepository.findAll();
        Division mockDivision = divisions.get(0);
        divisionService.addEmployees(mockDivision, testEmployee2);
        Assertions.assertTrue(mockDivision.getEmployees().contains(testEmployee2));
    }

    @Test
    void addEmployeesTest() {
        List<Employee> employees = new ArrayList<>(Arrays.asList(testEmployee2, testEmployee3));
        employees.forEach(e -> {
            Assertions.assertFalse(testDivision.getEmployees().contains(e));
        });
        divisionService.addEmployees(testDivision, employees);
        employees.forEach(e -> {
            Assertions.assertTrue(testDivision.getEmployees().contains(e));
        });
    }
}
