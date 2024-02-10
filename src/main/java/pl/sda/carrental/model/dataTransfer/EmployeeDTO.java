package pl.sda.carrental.model.dataTransfer;

import lombok.*;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.model.entity.userEntities.Role;

import java.util.Set;

@Data
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private Role role;
    private DivisionDTO divisionDTO;
    private Employee.Position position;


    @AllArgsConstructor
    @Data
    public static class DivisionDTO {
        private Long divisionId;
        private String divisionString;
    }
}
