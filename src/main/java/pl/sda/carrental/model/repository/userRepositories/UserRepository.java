package pl.sda.carrental.model.repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.carrental.model.entity.Reservation;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
