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
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.repository.dto.DivisionDto;
import pl.sda.carrental.model.repository.dto.ReservationDto;
import pl.sda.carrental.model.repository.mapper.CarMapper;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.model.repository.mapper.DivisionMapper;
import pl.sda.carrental.model.repository.mapper.ReservationMapper;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    public void makeReservation(ReservationDto dto, User user, CarDto carDto){

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
        else if (reservation.isInsurance() == true && reservation.isGoing_abroad() == false) {
            reservation.setCost(totalCost.add(insurance));
        }
        else if (reservation.isInsurance() == false && reservation.isGoing_abroad() == true) {
            reservation.setCost(totalCost.add(going_abroad));
        }
        else if (reservation.isInsurance() == false && reservation.isGoing_abroad() == false) {
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
        return customer;
    }

    public void deleteReservation(){
        List<ReservationDto> reservationsList = getListOfReservations();
        Date todayDate = new Date();
        for(int i = 0; i < reservationsList.size(); i++){
            if(todayDate == reservationsList.get(i).getReservation_end()){
                reservationsList.get(i).getCar().setStatus(Car.RentStatus.AVAILABLE);
                reservationRepository.deleteById(reservationsList.get(i).getId());
            }
        }
    }
    public CarDto getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            return CarMapper.map(carOptional.get());
        }
        throw new EntityNotFoundException();
    }
    public ReservationDto getReservationById(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            return ReservationMapper.map(reservationOptional.get());
        }
        throw new EntityNotFoundException();
    }
    public List<ReservationDto> getListOfReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return ReservationMapper.mapEntityListToDtoList(reservations);
    }
    public List<CarDto> getListOfCars(){
        List<Car> cars = carRepository.findAll();
        return CarMapper.mapEntityListToDtoList(cars);
    }
    public List<DivisionDto> getListOfDivision(){
        List<Division> divisions = divisionRepository.findAll();
        return DivisionMapper.mapEntityListToDtoList(divisions);
    }
}
