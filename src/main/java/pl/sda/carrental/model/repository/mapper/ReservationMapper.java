package pl.sda.carrental.model.repository.mapper;

import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.repository.dto.ReservationDto;
import java.util.ArrayList;
import java.util.List;
public class ReservationMapper {
    public static ReservationDto map(Reservation reservation){
        ReservationDto  ReservationDto = new ReservationDto();
        ReservationDto.setId(reservation.getId());
        ReservationDto.setCar(reservation.getCar());
        ReservationDto.setCustomer(reservation.getCustomer());
        ReservationDto.setRental_division(reservation.getRental_division());
        ReservationDto.setReturn_division(reservation.getReturn_division());
        ReservationDto.setReservation_start(reservation.getReservation_start());
        ReservationDto.setReservation_end(reservation.getReservation_end());
        ReservationDto.setCost(reservation.getCost());
        ReservationDto.setBookCarStatus(reservation.getBookCarStatus());
        return ReservationDto;
    }
    public static Reservation map(ReservationDto reservationdto){
        Reservation  Reservation = new Reservation();
        Reservation.setId(reservationdto.getId());
        Reservation.setCar(reservationdto.getCar());
        Reservation.setCustomer(reservationdto.getCustomer());
        Reservation.setRental_division(reservationdto.getRental_division());
        Reservation.setReturn_division(reservationdto.getReturn_division());
        Reservation.setReservation_start(reservationdto.getReservation_start());
        Reservation.setReservation_end(reservationdto.getReservation_end());
        Reservation.setCost(reservationdto.getCost());
        Reservation.setBookCarStatus(reservationdto.getBookCarStatus());
        return Reservation;
    }
    public static List<ReservationDto> mapEntityListToDtoList(List<Reservation> reservations){

        List<ReservationDto> result = new ArrayList<>();
        for (Reservation reservation: reservations) {
            result.add(map(reservation));
        }
        return result;
    }
    public static List<Reservation> mapDtoListToEntityList(List<ReservationDto> dtos){

        List<Reservation> result = new ArrayList<>();
        for (ReservationDto dto: dtos) {
            result.add(map(dto));
        }
        return result;
    }
}
