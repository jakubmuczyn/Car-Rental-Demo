package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.CarRental;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
}
