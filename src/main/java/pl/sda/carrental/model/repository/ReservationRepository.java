package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
