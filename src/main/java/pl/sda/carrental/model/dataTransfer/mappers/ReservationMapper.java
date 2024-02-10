package pl.sda.carrental.model.dataTransfer.mappers;

import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.dataTransfer.ReservationDTO;
import java.util.ArrayList;
import java.util.List;
public class ReservationMapper {
    public static ReservationDTO map(Reservation reservation){
        ReservationDTO ReservationDto = new ReservationDTO();
        ReservationDto.setId(reservation.getId());
        ReservationDto.setCar(reservation.getCar());
        ReservationDto.setCustomer(reservation.getCustomer());
        ReservationDto.setRental_division(reservation.getRental_division());
        ReservationDto.setReturn_division(reservation.getReturn_division());
        ReservationDto.setReservation_start(reservation.getReservation_start());
        ReservationDto.setReservation_end(reservation.getReservation_end());
        ReservationDto.setCost(reservation.getCost());
        ReservationDto.setInsurance(reservation.isInsurance());
        ReservationDto.setGoing_abroad(reservation.isGoing_abroad());
        return ReservationDto;
    }
    public static Reservation map(ReservationDTO reservationdto){
        Reservation  Reservation = new Reservation();
        Reservation.setId(reservationdto.getId());
        Reservation.setCar(reservationdto.getCar());
        Reservation.setCustomer(reservationdto.getCustomer());
        Reservation.setRental_division(reservationdto.getRental_division());
        Reservation.setReturn_division(reservationdto.getReturn_division());
        Reservation.setReservation_start(reservationdto.getReservation_start());
        Reservation.setReservation_end(reservationdto.getReservation_end());
        Reservation.setCost(reservationdto.getCost());
        Reservation.setInsurance(reservationdto.isInsurance());
        Reservation.setGoing_abroad(reservationdto.isGoing_abroad());
        return Reservation;
    }
    public static List<ReservationDTO> mapEntityListToDtoList(List<Reservation> reservations){

        List<ReservationDTO> result = new ArrayList<>();
        for (Reservation reservation: reservations) {
            result.add(map(reservation));
        }
        return result;
    }
    public static List<Reservation> mapDtoListToEntityList(List<ReservationDTO> dtos){

        List<Reservation> result = new ArrayList<>();
        for (ReservationDTO dto: dtos) {
            result.add(map(dto));
        }
        return result;
    }
}
