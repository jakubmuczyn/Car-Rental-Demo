package pl.sda.carrental.model.dto;

import pl.sda.carrental.model.entity.Division;
import pl.sda.carrental.model.entity.userEntities.Employee;

public class EmployeeDto extends UserDTO{
    private Employee.Position posistion;
    private Division division;
}
