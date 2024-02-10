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
import java.util.HashSet;
import java.util.Set;

//test
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
        Role adminRole = Role.builder().name(PrincipalRole.ADMIN.name()).build();
        Role employeeRole = Role.builder().name(PrincipalRole.EMPLOYEE.name()).build();
        Role customerRole = Role.builder().name(PrincipalRole.CUSTOMER.name()).build();
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
        Address address_2 = Address.builder()
                .address("Pod testami 18")
                .state("Wielkopolskie")
                .city("Poznań")
                .build();

        Division division = Division.builder()
                .address(address)
                .build();

        Employee employee = Employee.builder()
                .division(division)
                .name("Jan Kowalski")
                .position(Employee.Position.EMPLOYEE)
                .email("jan.kowalski@company.com")
                .password(passwordEncoder.encode("pracownik"))
                .username("pracownik")
                .roles(new HashSet<>(Set.of(roleService.getRoleByEnum(PrincipalRole.EMPLOYEE))))
                .build();

        Employee employee2 = Employee.builder()
                .division(division)
                .name("Maciej Nowak")
                .position(Employee.Position.EMPLOYEE)
                .email("maciej.nowak@company.com")
                .password(passwordEncoder.encode("nowak"))
                .username("nowak")
                .roles(new HashSet<>(Set.of(roleService.getRoleByEnum(PrincipalRole.EMPLOYEE))))
                .build();

        Customer customer = Customer.builder()
            .name("Maciej Konsument")
            .email("maciej.konsument@gmail.com")
            .password(passwordEncoder.encode("klient"))
            .roles(new HashSet<>(Set.of(roleService.getRoleByEnum(PrincipalRole.CUSTOMER))))
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
            .status(Car.RentStatus.AVAILABLE)
            .division(division)
            .build();

        addressRepository.save(address);
        addressRepository.save(address_2);
        divisionRepository.save(division);
        carRepository.save(car);
        division.addCar(car);
        divisionRepository.save(division);


        division.addEmployee(employee);

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        customerRepository.save(customer);

        division.setManager(employee);
        divisionRepository.save(division);

        Reservation reservation = Reservation.builder()
            .rental_division(division)
            .return_division(division)
            .employee(employee)
            .customer(customer)
            .car(car)
            .reservation_start(LocalDateTime.now())
            .reservation_end(LocalDateTime.now().plusDays(7))
            .cost(new BigDecimal("20.50"))
            .reservation_date(LocalDate.now())
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