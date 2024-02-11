package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.UserDisplayDTO;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;
import pl.sda.carrental.service.DivisionService;
import pl.sda.carrental.service.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class UserMapper {
    private final UserService userService;
    private final EmployeeRepository employeeRepository;

    public UserMapper(UserService userService, EmployeeRepository employeeRepository) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
    }
    
    public <T extends User> UserDisplayDTO getUserDisplayDto(T user) {
        List<Employee> activeNonManagers = employeeRepository.findAllActiveNonManagers();
        boolean isEmployee = employeeRepository.existsById(user.getId());
        boolean isManager = isEmployee && user.isActive() ? employeeRepository.findAllActiveNonManagers().stream().noneMatch(e -> Objects.equals(e.getId(), user.getId())) : false;

        return UserDisplayDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .principalRole(user.getRole().getRoleName())
                .isActive(user.isActive())
                .isManager(isManager)
                .build();
    }
    
    public <T extends User> List<UserDisplayDTO> getUserDisplayDtos(List<T> users) {
        return users.stream().map(this::getUserDisplayDto).toList();
    }
}
