package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.carrental.model.entity.Car;
import pl.sda.carrental.model.entity.Reservation;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Override
    List<Car> findAll();
}
