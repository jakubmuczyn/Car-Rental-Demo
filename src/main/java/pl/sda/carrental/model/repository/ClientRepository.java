package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
