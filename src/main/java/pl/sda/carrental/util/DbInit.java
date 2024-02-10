package pl.sda.carrental.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.entity.*;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.repository.*;
import pl.sda.carrental.model.repository.userRepositories.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//test
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
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private final CarRentalRepository carRentalRepository;
    private final TransactionRepository transactionRepository;

    @PostConstruct
    private void postConstruct() {
        Role adminRole = Role.builder().name("ADMIN").build();
        Role employeeRole = Role.builder().name("EMPLOYEE").build();
        Role customerRole = Role.builder().name("CUSTOMER").build();
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

        Address address2 = Address.builder()
                .address("Testowa 34")
                .state("Małopolskie")
                .city("Kraków")
                .build();

        Division division = Division.builder()
                .address(address)
                .build();

        Division division2 = Division.builder()
                .address(address2)
                .build();

        Employee employee = Employee.builder()
                .division(division)
                .name("Jan Kowalski")
                .position(Employee.Position.EMPLOYEE)
                .email("jan.kowalski@company.com")
                .password(passwordEncoder.encode("pracownik"))
                .username("pracownik")
                .build();

        Customer customer = Customer.builder()
            .name("Maciej Konsument")
            .email("maciej.konsument@gmail.com")
            .password(passwordEncoder.encode("klient"))
            .username("klient")
            .build();

        Customer customer2 = Customer.builder()
                .name("Mariusz Konsument")
                .email("mariusz.konsument@gmail.com")
                .password(passwordEncoder.encode("klient2"))
                .username("klient2")
                .build();

        Car car = Car.builder()
            .brand("Toyota")
            .model("Corolla")
            .production_year(Year.of(2020))
            .body_type("Sedan")
            .cost_per_day(new BigDecimal("200"))
            .mileage(260000)
            .color("Blue")
            .status(Car.RentStatus.AVAILABLE)
            .division(division)
            .build();

        Car car1 = Car.builder()
                .brand("Audi")
                .model("A4")
                .production_year(Year.of(2022))
                .body_type("Sedan")
                .cost_per_day(new BigDecimal("1000"))
                .mileage(22000)
                .color("Grey")
                .status(Car.RentStatus.AVAILABLE)
                .division(division)
                .build();

        addressRepository.save(address);
        addressRepository.save(address2);
        divisionRepository.save(division);
        divisionRepository.save(division2);
        carRepository.save(car);
        carRepository.save(car1);
        division.addCar(car);
        divisionRepository.save(division);


        division.addEmployee(employee);

        employeeRepository.save(employee);
        customerRepository.save(customer);
        customerRepository.save(customer2);

        Date date = new Date();
        Reservation reservation = Reservation.builder()
            .rental_division("Kraków")
            .return_division("Kraków")
            .customer(customer)
            .car(car)
            .reservation_start(date)
            .reservation_end(date)
            .cost(new BigDecimal("20.50"))
            .insurance(true)
            .going_abroad(true)
            .build();

        reservationRepository.save(reservation);
        car.setReservation(reservation);
        carRepository.save(car);

        CarRental carRental = CarRental.builder()
            .rentalStatus(CarRental.RentalStatus.ONGOING)
            .employee(employee)
            .rentalDate(LocalDate.now())
            .reservation(reservation)
            .comment("Test comment")
            .build();

        carRentalRepository.save(carRental);

        Transaction transaction = Transaction.builder()
            .carRental(carRental)
            .transactionDate(LocalDate.now())
            .transactionAmount(carRental.getReservation().getCost())
            .build();

        transactionRepository.save(transaction);

        testPrint();
        System.out.println(division.getCars());
    }

    private void testPrint() {
        System.out.println("Address query: " + addressRepository.findAll().get(0).toString());
        System.out.println("Employee query: " + employeeRepository.findAll().get(0).toString());
        System.out.println("Client query: " + customerRepository.findAll().get(0).toString());
        System.out.println("Car query: " + carRepository.findAll().get(0).toString());
        System.out.println("Division query: " + divisionRepository.findAll().get(0).toString());
        System.out.println("Reservation query: " + reservationRepository.findAll().get(0).toString());
        System.out.println("CarRental query: " + carRentalRepository.findAll().get(0).toString());
        System.out.println("Transaction query: " + transactionRepository.findAll().get(0).toString());
    }
}