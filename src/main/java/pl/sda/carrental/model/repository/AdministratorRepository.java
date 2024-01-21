package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
