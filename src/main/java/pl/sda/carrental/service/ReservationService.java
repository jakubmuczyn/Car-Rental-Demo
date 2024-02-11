package pl.sda.carrental.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.ReservationRepository;
import pl.sda.carrental.model.dataTransfer.CarDTO;
import pl.sda.carrental.model.dataTransfer.DivisionDTO;
import pl.sda.carrental.model.dataTransfer.ReservationDTO;
import pl.sda.carrental.model.dataTransfer.mappers.CarMapper;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.model.dataTransfer.mappers.DivisionMapper;
import pl.sda.carrental.model.dataTransfer.mappers.ReservationMapper;
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class ReservationService{

    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private final DivisionRepository divisionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public void makeReservation(ReservationDTO dto, User user, CarDTO carDto){

        Customer customer = UserToCustomer(user);
        Car car = new Car();
        car = CarMapper.map(carDto);

        Reservation reservation = Reservation.builder()
                .rental_division(dto.getRental_division())
                .return_division(dto.getReturn_division())
                .customer(customer)
                .car(car)
                .reservation_start(dto.getReservation_start())
                .reservation_end(dto.getReservation_end())
                .cost(dto.getCost())
                .insurance(dto.isInsurance())
                .going_abroad(dto.isGoing_abroad())
                .build();

        Date start = reservation.getReservation_start();
        Date end = reservation.getReservation_end();
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        BigDecimal totalCost = BigDecimal.valueOf(diff).multiply(car.getCost_per_day());
        BigDecimal insurance = BigDecimal.valueOf(diff).multiply(BigDecimal.valueOf(79));
        BigDecimal going_abroad = BigDecimal.valueOf(100);
        if(reservation.isInsurance() && reservation.isGoing_abroad()){
            reservation.setCost(totalCost.add(insurance).add(going_abroad));
        }
        else if (reservation.isInsurance() && !reservation.isGoing_abroad()) {
            reservation.setCost(totalCost.add(insurance));
        }
        else if (!reservation.isInsurance() && reservation.isGoing_abroad()) {
            reservation.setCost(totalCost.add(going_abroad));
        }
        else if (!reservation.isInsurance() && !reservation.isGoing_abroad()) {
            reservation.setCost(totalCost);
        }

        reservationRepository.save(reservation);
        customer.addReservation(reservation);
        car.addReservationId(reservation);
        car.setStatus(Car.RentStatus.RENTED);
        userRepository.save(customer);
        carRepository.save(car);
    }
    public Customer UserToCustomer(User user){
        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setName(user.getName());
        customer.setUsername(user.getUsername());
        customer.setEmail(user.getEmail());
        customer.setPassword(user.getPassword());
        customer.setRole(roleRepository.getById(3));
        return customer;
    }

    public void deleteReservation(){
        List<ReservationDTO> reservationsList = getListOfReservations();
        Date todayDate = new Date();
        for (ReservationDTO reservationDTO : reservationsList) {
            if (todayDate == reservationDTO.getReservation_end()) {
                reservationDTO.getCar().setStatus(Car.RentStatus.AVAILABLE);
                reservationRepository.deleteById(reservationDTO.getId());
            }
        }
    }
    public CarDTO getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            return CarMapper.map(carOptional.get());
        }
        throw new EntityNotFoundException();
    }
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            return ReservationMapper.map(reservationOptional.get());
        }
        throw new EntityNotFoundException();
    }
    public List<ReservationDTO> getListOfReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return ReservationMapper.mapEntityListToDtoList(reservations);
    }
    public List<CarDTO> getListOfCars(){
        List<Car> cars = carRepository.findAll();
        return CarMapper.mapEntityListToDtoList(cars);
    }
    public List<DivisionDTO> getListOfDivision(){
        List<Division> divisions = divisionRepository.findAll();
        return DivisionMapper.mapEntityListToDtoList(divisions);
    }
}
