package pl.sda.carrental.model.repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.userEntities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
