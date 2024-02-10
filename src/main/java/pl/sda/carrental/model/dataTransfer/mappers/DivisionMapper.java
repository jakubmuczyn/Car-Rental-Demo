package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.DivisionDTO;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.repository.DivisionRepository;

import java.util.List;

@Service
public class DivisionMapper {
    private final DivisionRepository divisionRepository;
    private final EmployeeMapper employeeMapper;

    public DivisionMapper(DivisionRepository divisionRepository, EmployeeMapper employeeMapper) {
        this.divisionRepository = divisionRepository;
        this.employeeMapper = employeeMapper;
    }

    public DivisionDTO getDivisionDTO(Division division) {
        List<EmployeeDTO> employees = division.getEmployees().stream().map(employeeMapper::getDto).toList();
        return DivisionDTO.builder()
                .division_id(division.getDivision_id())
                .address(division.getAddress())
                .employees(employees)
                .build();
    }

    public Division getDivisionObject(DivisionDTO divisionDTO) {
        Division division = divisionRepository.findById(divisionDTO.getDivision_id()).get();

        return Division.builder()
            .division_id(divisionDTO.getDivision_id())
            .address(divisionDTO.getAddress())
            .employees(division.getEmployees())
            .cars(division.getCars())
            .build();
    }
}
