package pl.sda.carrental.service;

import ch.qos.logback.core.net.server.Client;
import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.*;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.model.repository.userRepositories.UserRepository;
import pl.sda.carrental.security.PrincipalRole;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AdministratorRepository administratorRepository;
    private final UserRepository<User> userRepository;

    public UserService(UserRepository<User> userRepository, AdministratorRepository adminRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository1, AdministratorRepository administratorRepository, UserRepository userRepository1) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository1;
        this.administratorRepository = administratorRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public <T extends User> Role getPrincipalRole(T user) {
        return user.getRole();
    }

    public <T extends User> JpaRepository<T, Long> getRepository(T user) {
        JpaRepository<T, Long>  repository;
        String roleName = user.getRole().getRoleName();

        if (roleName.equals(PrincipalRole.ADMIN.name())) {
            return (JpaRepository<T, Long>) administratorRepository;
        } else if (roleName.equals(PrincipalRole.EMPLOYEE.name())) {
            return (JpaRepository<T, Long>) employeeRepository;
        } else {
            return (JpaRepository<T, Long>) customerRepository;
        }
    }

    public <T extends User> void saveUser(T editedUser) {
        JpaRepository<T, Long>  repository = getRepository(editedUser);
        T user = repository.findById(editedUser.getId()).get();
        editedUser.setRole(user.getRole());
        repository.save(editedUser);
    }

    public void toggle(Long userId) {
        User user = userRepository.findById(userId).get();
        user.setActive(!user.isActive());
        userRepository.save(user);
    }
}
