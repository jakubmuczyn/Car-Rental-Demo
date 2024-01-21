package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.carrental.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
