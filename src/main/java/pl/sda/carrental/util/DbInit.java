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
import java.time.Year;
import java.util.Date;

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
        Address address_3 = Address.builder()
                .address("Dj'a tiestio 15")
                .state("Kujawsko-Pomorskie")
                .city("Bydgoszcz")
                .build();

        addressRepository.save(address);
        addressRepository.save(address_2);
        addressRepository.save(address_3);


        Division division = Division.builder()
                .address(address)
                .build();

        Division division2 = Division.builder()
                .address(address_2)
                .build();

        divisionRepository.save(division);
        divisionRepository.save(division2);

        Role adminRole = Role.builder().roleName(PrincipalRole.ADMIN.name()).build();
        Role employeeRole = Role.builder().roleName(PrincipalRole.EMPLOYEE.name()).build();
        Role customerRole = Role.builder().roleName(PrincipalRole.CUSTOMER.name()).build();

        roleRepository.save(adminRole);
        roleRepository.save(employeeRole);
        roleRepository.save(customerRole);

        Administrator dbTestAdmin = Administrator.builder()
                .username("admin")
                .name("Admin Adminowy")
                .email("admin@test.pl")
                .role(adminRole)
                .password(passwordEncoder
                .encode("admin"))
                .build();

        administratorRepository.save(dbTestAdmin);

        Employee dbTestManager = Employee.builder()
                .username("manager")
                .name("Wiktor Traktor")
                .email("wiktor.traktor@company.com")
                .position(Employee.Position.MANAGER)
                .role(employeeRole)
                .division(division)
                .password(passwordEncoder.encode("manager"))
                .build();
        employeeRepository.save(dbTestManager);

        Employee dbTestEmployee = Employee.builder()
                .username("employee")
                .name("Jan Kowalski")
                .email("jan.kowalski@company.com")
                .position(Employee.Position.EMPLOYEE)
                .role(employeeRole)
                .division(division)
                .password(passwordEncoder.encode("employee"))
                .build();

        Customer dbTestCustomer = Customer.builder()
                .username("customer")
                .name("Maciej Konsument")
                .email("maciej.konsument@gmail.com")
                .role(customerRole)
                .password(passwordEncoder.encode("customer"))
                .build();
        customerRepository.save(dbTestCustomer);

        Employee employee2 = Employee.builder()
                .division(division)
                .name("Maciej Nowak")
                .position(Employee.Position.EMPLOYEE)
                .email("maciej.nowak@company.com")
                .password(passwordEncoder.encode("nowak"))
                .username("nowak")
                .role(employeeRole)
                .build();

        Employee employee3 = Employee.builder()
                .division(division2)
                .name("Adam Nawałka")
                .position(Employee.Position.EMPLOYEE)
                .email("adam.nawalka@company.com")
                .password(passwordEncoder.encode("nawalka"))
                .username("nawalka")
                .role(employeeRole)
                .build();

        Employee employee4 = Employee.builder()
                .division(division2)
                .name("Konrad Paczko")
                .position(Employee.Position.EMPLOYEE)
                .email("konrad.paczko@company.com")
                .password(passwordEncoder.encode("konrad"))
                .username("konrad")
                .role(employeeRole)
                .build();

//        Customer customer = Customer.builder()
//            .name("Maciej Konsument")
//            .email("maciej.konsument@gmail.com")
//            .password(passwordEncoder.encode("klient"))
//            .role(customerRole)
//            .username("klient")
//            .build();

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


        carRepository.save(car);
        division.addCar(car);
        divisionRepository.save(division);
        Car dbTestCar = Car.builder()
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
        carRepository.save(dbTestCar);

        Car dbTestCar1 = Car.builder()
                .brand("Audi")
                .model("A4")
                .production_year(Year.of(2023))
                .body_type("Sedan")
                .cost_per_day(new BigDecimal("1000"))
                .mileage(12000)
                .color("Grey")
                .status(Car.RentStatus.AVAILABLE)
                .division(division)
                .build();
        carRepository.save(dbTestCar1);

        Car dbTestCar2 = Car.builder()
                .brand("Renault")
                .model("Clio")
                .production_year(Year.of(2019))
                .body_type("Hatchback")
                .cost_per_day(new BigDecimal("150"))
                .mileage(340000)
                .color("Silver")
                .status(Car.RentStatus.AVAILABLE)
                .division(division)
                .build();
        carRepository.save(dbTestCar2);

        Car dbTestCar3 = Car.builder()
                .brand("Hyundai")
                .model("i30")
                .production_year(Year.of(2020))
                .body_type("Sedan")
                .cost_per_day(new BigDecimal("200"))
                .mileage(220000)
                .color("Brown")
                .status(Car.RentStatus.AVAILABLE)
                .division(division)
                .build();
        carRepository.save(dbTestCar3);

        division.addEmployee(dbTestEmployee);
        division.addEmployee(employee2);
        division2.addEmployee(employee3);
        division2.addEmployee(employee4);

        employeeRepository.save(dbTestEmployee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        customerRepository.save(dbTestCustomer);

        division.setManager(employee2);
        division2.setManager(employee3);
        divisionRepository.save(division);
        divisionRepository.save(division2);

        Date date1 = new Date();
        Date date2 = new Date();

//        Reservation reservation = Reservation.builder()
//            .rental_division(division.getAddress().getCity())
//            .return_division(division.getAddress().getCity())
//            .customer(customer)
//            .car(car)
//            .reservation_start(date1)
//            .reservation_end(date2)
//            .cost(new BigDecimal("20.50"))
//            .build();

        Reservation dbTestReservation = Reservation.builder()
                .rental_division(division.getAddress().getCity())
                .return_division(division.getAddress().getCity())
                .customer(dbTestCustomer)
                .car(dbTestCar)
                .reservation_start(date1)
                .reservation_end(date2)
                .cost(new BigDecimal("20.50"))
                .build();

        reservationRepository.save(dbTestReservation);
        dbTestCustomer.addReservation(dbTestReservation);
        dbTestCar.addReservationId(dbTestReservation);
        dbTestCar.setStatus(Car.RentStatus.RENTED);
        customerRepository.save(dbTestCustomer);
        carRepository.save(dbTestCar);

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

        division.addEmployee(dbTestEmployee);
        division.addCar(dbTestCar);
        divisionRepository.save(division);

        dbTestCar.setReservation(dbTestReservation);
        carRepository.save(dbTestCar);

        testPrint();
        System.out.println(division.getCars());
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