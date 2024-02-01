package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.entity.*;
import pl.sda.carrental.model.entity.enums.RentStatus;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Client;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.entity.enums.Position;
import pl.sda.carrental.model.repository.*;
import pl.sda.carrental.model.repository.userRepositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class DbInit {
    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;


    @PostConstruct
    private void postConstruct() {
        Role adminRole = Role.builder().name("Admin").build();
        Role employeeRole = Role.builder().name("Employee").build();
        Role customerRole = Role.builder().name("Customer").build();
        roleRepository.save(adminRole);
        roleRepository.save(employeeRole);
        roleRepository.save(customerRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        Administrator admin = Administrator.builder().name("admin").username("admin").email("admin@test.pl").password(passwordEncoder.encode("admin")).roles(adminRoles).build();
        administratorRepository.save(admin);

        testDatabase();
    }

    private void testDatabase() {
        Address address = Address.builder()
                .address("Testowa 12")
                .state("Łódzkie")
                .city("Łódź")
                .build();

        Division division = Division.builder()
                .address(address)
                .build();

        Employee employee = Employee.builder()
                .division(division)
                .name("Jan Kowalski")
                .position(Position.EMPLOYEE)
                .email("jan.kowalski@company.com")
                .password(passwordEncoder.encode("pracownik"))
                .username("pracownik")
                .build();

        Client client = Client.builder()
            .name("Maciej Konsument")
            .email("maciej.konsument@gmail.com")
            .password(passwordEncoder.encode("klient"))
            .username("klient")
            .build();

        Car car = Car.builder()
            .brand("Toyota")
            .model("Corolla")
            .production_year(Year.of(2020))
            .body_type("Sedan")
            .cost_per_day(new BigDecimal("200"))
            .mileage(260000)
            .color("Blue")
            .status(RentStatus.AVAILABLE)
            .division(division)
            .build();

        addressRepository.save(address);
        divisionRepository.save(division);
        carRepository.save(car);
        division.addCar(car);
        divisionRepository.save(division);


        division.addEmployee(employee);

        employeeRepository.save(employee);
        clientRepository.save(client);


        Reservation reservation = Reservation.builder()
            .rental_division(division)
            .return_division(division)
            .employee(employee)
            .client(client)
            .car(car)
            .reservation_start(LocalDateTime.now())
            .reservation_end(LocalDateTime.now().plusDays(7))
            .cost(new BigDecimal("20.50"))
            .reservation_date(LocalDate.now())
            .build();

        reservationRepository.save(reservation);
        car.setReservation(reservation);
        carRepository.save(car);

        System.out.println("Address query: " + addressRepository.findAll().get(0).toString());
        System.out.println("Employee query: " + employeeRepository.findAll().get(0).toString());
        System.out.println("Client query: " + clientRepository.findAll().get(0).toString());
        System.out.println("Car query: " + carRepository.findAll().get(0).toString());
        System.out.println("Division query: " + divisionRepository.findAll().get(0).toString());
        System.out.println("Reservation query: " + reservationRepository.findAll().get(0).toString());

        System.out.println(division.getCars());


    }
}