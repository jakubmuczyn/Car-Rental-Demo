package pl.sda.carrental.model.repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.userEntities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
