package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.DivisionRepository;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;

@Service
public class EmployeeMapper implements UserDtoMapper<Employee, EmployeeDTO> {
    
    private final EmployeeRepository employeeRepository;
    private final DivisionRepository divisionRepository;
    
    public EmployeeMapper(EmployeeRepository employeeRepository, DivisionRepository divisionRepository) {
        this.employeeRepository = employeeRepository;
        this.divisionRepository = divisionRepository;
    }
    
    @Override
    public Employee getUserClass(EmployeeDTO dto) {
        Employee employee = employeeRepository.getReferenceById(dto.getId());
        Division division = divisionRepository.findById(dto.getDivisionDTO().getDivisionId()).get();
        
        return Employee.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .name(dto.getName())
                .email(dto.getEmail())
                .position(dto.getPosition())
                .role(dto.getRole())
                .division(division)
                .password(employee.getPassword())
                .isActive(dto.isActive())
                .build();
    }
    
    @Override
    public EmployeeDTO getDto(Employee userClass) {
        EmployeeDTO.DivisionDTO divisionDTO = new EmployeeDTO.DivisionDTO(userClass.getDivision().getDivision_id(), userClass.getDivision().getAddress().toString());
        return EmployeeDTO.builder()
                .id(userClass.getId())
                .name(userClass.getName())
                .role(userClass.getRole())
                .email(userClass.getEmail())
                .isActive(userClass.isActive())
                .role(userClass.getRole())
                .position(userClass.getPosition())
                .divisionDTO(divisionDTO)
                .build();
    }
}
