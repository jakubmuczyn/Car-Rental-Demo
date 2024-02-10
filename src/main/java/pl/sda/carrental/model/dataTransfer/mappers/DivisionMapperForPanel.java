package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.DivisionDTOForPanel;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.repository.DivisionRepository;

import java.util.List;

@Service
public class DivisionMapperForPanel {
    private final DivisionRepository divisionRepository;
    private final EmployeeMapper employeeMapper;

    public DivisionMapperForPanel(DivisionRepository divisionRepository, EmployeeMapper employeeMapper) {
        this.divisionRepository = divisionRepository;
        this.employeeMapper = employeeMapper;
    }

    public DivisionDTOForPanel getDivisionDTO(Division division) {
        List<EmployeeDTO> employees = division.getEmployees().stream().map(employeeMapper::getDto).toList();
        return DivisionDTOForPanel.builder()
                .division_id(division.getDivision_id())
                .address(division.getAddress())
                .employees(employees)
                .manager(employeeMapper.getDto(division.getManager()))
                .build();
    }

    public Division getDivisionObject(DivisionDTOForPanel divisionDTO) {
        Division division = divisionRepository.findById(divisionDTO.getDivision_id()).get();

        return Division.builder()
            .division_id(divisionDTO.getDivision_id())
            .address(divisionDTO.getAddress())
            .employees(division.getEmployees())
            .cars(division.getCars())
            .manager(employeeMapper.getUserClass(divisionDTO.getManager()))
            .build();
    }
}
