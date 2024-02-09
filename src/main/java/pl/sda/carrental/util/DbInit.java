package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.entity.*;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.repository.*;
import pl.sda.carrental.model.repository.userRepositories.*;
import pl.sda.carrental.security.PrincipalRole;
import pl.sda.carrental.service.RoleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

@Getter
@Setter
@RequiredArgsConstructor
@Component
@Profile("dev")
public class DbInit {
    private final AdministratorRepository administratorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private final CarRentalRepository carRentalRepository;
    private final TransactionRepository transactionRepository;
    private final RoleService roleService;

    @PostConstruct
    private void postConstruct() {
        testDatabase();
    }

    private void testDatabase() {
        Address dbTestAddress = Address.builder()
                .address("Testowa 12")
                .state("Łódzkie")
                .city("Łódź")
                .build();
        addressRepository.save(dbTestAddress);
        
        Division dbTestDivision = Division.builder()
                .address(dbTestAddress)
                .build();
        divisionRepository.save(dbTestDivision);
        
        Role adminRole = Role.builder().roleName(PrincipalRole.ADMIN.name()).build();
        Role employeeRole = Role.builder().roleName(PrincipalRole.EMPLOYEE.name()).build();
        Role customerRole = Role.builder().roleName(PrincipalRole.CUSTOMER.name()).build();
        roleRepository.save(adminRole);
        roleRepository.save(employeeRole);
        roleRepository.save(customerRole);
        
        Administrator dbTestAdmin = Administrator.builder()
                .name("Admin Adminowy")
                .username("admin")
                .email("admin@test.pl")
                .password(passwordEncoder
                        .encode("admin"))
                .role(adminRole)
                .build();
        administratorRepository.save(dbTestAdmin);
        
        Employee dbTestManager = Employee.builder()
                .division(dbTestDivision)
                .name("Wiktor Traktor")
                .position(Employee.Position.MANAGER)
                .email("wiktor.traktor@company.com")
                .password(passwordEncoder.encode("manager"))
                .username("manager")
                .role(employeeRole)
                .build();
        employeeRepository.save(dbTestManager);
        
        Employee dbTestEmployee = Employee.builder()
                .division(dbTestDivision)
                .name("Jan Kowalski")
                .position(Employee.Position.EMPLOYEE)
                .email("jan.kowalski@company.com")
                .password(passwordEncoder.encode("employee"))
                .username("employee")
                .role(employeeRole)
                .build();
        employeeRepository.save(dbTestEmployee);
        
        Customer dbTestCustomer = Customer.builder()
                .name("Maciej Konsument")
                .email("maciej.konsument@gmail.com")
                .password(passwordEncoder.encode("customer"))
                .role(customerRole)
                .username("customer")
                .build();
        customerRepository.save(dbTestCustomer);
        
        Car dbTestCar = Car.builder()
                .brand("Toyota")
                .model("Corolla")
                .production_year(Year.of(2020))
                .body_type("Sedan")
                .cost_per_day(new BigDecimal("200"))
                .mileage(260000)
                .color("Blue")
                .status(Car.RentStatus.AVAILABLE)
                .division(dbTestDivision)
                .build();
        carRepository.save(dbTestCar);
        
        Reservation dbTestReservation = Reservation.builder()
                .rental_division(dbTestDivision)
                .return_division(dbTestDivision)
                .employee(dbTestEmployee)
                .customer(dbTestCustomer)
                .car(dbTestCar)
                .reservation_start(LocalDateTime.now())
                .reservation_end(LocalDateTime.now().plusDays(7))
                .cost(new BigDecimal("20.50"))
                .reservation_date(LocalDate.now())
                .build();
        reservationRepository.save(dbTestReservation);
        
        CarRental dbTestCarRental = CarRental.builder()
                .rentalStatus(CarRental.RentalStatus.ONGOING)
                .employee(dbTestEmployee)
                .rentalDate(LocalDate.now())
                .reservation(dbTestReservation)
                .comment("Test comment")
                .build();
        carRentalRepository.save(dbTestCarRental);
        
        Transaction dbTestTransaction = Transaction.builder()
                .carRental(dbTestCarRental)
                .transactionDate(LocalDate.now())
                .transactionAmount(dbTestCarRental.getReservation().getCost())
                .build();
        transactionRepository.save(dbTestTransaction);
        
        dbTestDivision.addEmployee(dbTestEmployee);
        dbTestDivision.addCar(dbTestCar);
        divisionRepository.save(dbTestDivision);
        
        dbTestCar.setReservation(dbTestReservation);
        carRepository.save(dbTestCar);

        testPrint();
        System.out.println(dbTestDivision.getCars());
    }

    private void testPrint() {
        System.out.println("Address query: " + addressRepository.findAll().get(0).toString());
        System.out.println("Division query: " + divisionRepository.findAll().get(0).toString());
        System.out.println("Admin query: " + administratorRepository.findAll().get(0).toString());
        System.out.println("Manager query: " + employeeRepository.findAll().get(0).toString());
        System.out.println("Employee query: " + employeeRepository.findAll().get(1).toString());
        System.out.println("Customer query: " + customerRepository.findAll().get(0).toString());
        System.out.println("Car query: " + carRepository.findAll().get(0).toString());
        System.out.println("Reservation query: " + reservationRepository.findAll().get(0).toString());
        System.out.println("CarRental query: " + carRentalRepository.findAll().get(0).toString());
        System.out.println("Transaction query: " + transactionRepository.findAll().get(0).toString());
    }
}