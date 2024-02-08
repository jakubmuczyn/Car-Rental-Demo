package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.EmployeeDto;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeMapper implements UserDtoMapper<Employee, EmployeeDto> {

    private final EmployeeRepository employeeRepository;
    private final DivisionRepository divisionRepository;

    public EmployeeMapper(EmployeeRepository employeeRepository, DivisionRepository divisionRepository) {
        this.employeeRepository = employeeRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public Employee getUserClass(EmployeeDto dto) {
        Employee employee = employeeRepository.getReferenceById(dto.getId());
        Division division = divisionRepository.findById(dto.getDivisionDTO().getDivisionId()).get();

        return Employee.builder()
                .id(dto.getId())
                .roles(dto.getRoles())
                .name(dto.getName())
                .password(employee.getPassword())
                .isActive(dto.isActive())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .position(dto.getPosition())
                .division(division)
                .build();
    }

    @Override
    public EmployeeDto getDto(Employee u) {
        EmployeeDto.DivisionDTO divisionDTO = new EmployeeDto.DivisionDTO(u.getDivision().getDivision_id(), u.getDivision().getAddress().toString());
        return EmployeeDto.builder()
            .id(u.getId())
            .name(u.getName())
            .username(u.getUsername())
            .email(u.getEmail())
            .isActive(u.isActive())
            .roles(u.getRoles())
            .position(u.getPosition())
            .divisionDTO(divisionDTO)
            .build();
    }

}
