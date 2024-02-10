package pl.sda.carrental.model.repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.entity.userEntities.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
