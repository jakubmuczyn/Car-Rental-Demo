package pl.sda.carrental.model.repository.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.carrental.model.entity.userEntities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    List<Employee> findAllByIsActiveIsTrue();

    @Query("SELECT e FROM Employee e where e.isActive = True and e.id not in (select manager.id from Division)")
    List<Employee> findAllActiveNonManagers();
}
