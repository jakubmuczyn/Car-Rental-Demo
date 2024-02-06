package pl.sda.carrental.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.ReservationRepository;
import pl.sda.carrental.model.repository.dto.CarDto;
import pl.sda.carrental.model.repository.dto.CustomUserDetails;
import pl.sda.carrental.model.repository.dto.DivisionDto;
import pl.sda.carrental.model.repository.dto.ReservationDto;
import pl.sda.carrental.model.repository.mapper.CarMapper;
import pl.sda.carrental.model.repository.CarRepository;
import pl.sda.carrental.model.repository.mapper.DivisionMapper;
import pl.sda.carrental.model.repository.mapper.ReservationMapper;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService{

    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final DivisionRepository divisionRepository;

    public Reservation makeReservation(ReservationDto dto){
        Reservation reservation = ReservationMapper.map(dto);
        CustomUserDetails userDetails = getCurrentUser();
        Optional<User> user = userRepository.findById(userDetails.getId());
        user.ifPresent(reservation::setCustomer);
        reservationRepository.save(reservation);
        return reservation;
    }
    public ReservationDto getById(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            return ReservationMapper.map(reservationOptional.get());
        }
        throw new EntityNotFoundException();
    }
    public List<CarDto> getListOfCars(){
        List<Car> cars = carRepository.findAll();
        return CarMapper.mapEntityListToDtoList(cars);
    }
    public List<DivisionDto> getListOfDivision(){
        List<Division> divisions = divisionRepository.findAll();
        return DivisionMapper.mapEntityListToDtoList(divisions);
    }
    /*
    public List<ReservationDto> getListOfReservations(){
        CustomUserDetails userDetails = getCurrentUser();
        Optional<User> user = userRepository.findById(userDetails.getId());
        List<Reservation> reservations = reservationRepository.findAllByCreatedBy(user.get());
        return ReservationMapper.mapEntityListToDtoList(reservations);
    }

     */
    private CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
