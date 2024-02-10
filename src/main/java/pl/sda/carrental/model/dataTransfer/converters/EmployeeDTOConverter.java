package pl.sda.carrental.model.dataTransfer.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.sda.carrental.model.dataTransfer.EmployeeDTO;
import pl.sda.carrental.model.dataTransfer.mappers.EmployeeMapper;
import pl.sda.carrental.model.repository.userRepositories.EmployeeRepository;

@Component
public class EmployeeDTOConverter implements Converter<String, EmployeeDTO> {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTOConverter(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO convert(String source) {
        long employeeId = Long.parseLong(source);
        return employeeMapper.getDto(employeeRepository.getReferenceById(employeeId));
    }
}
