package pl.sda.carrental.model.dataTransfer;


import lombok.Builder;
import lombok.Data;
import pl.sda.carrental.model.entity.userEntities.Employee;
import pl.sda.carrental.security.PrincipalRole;

@Builder
@Data
public class UserDisplayDTO {
    private long id;
    private String username;
    private String name;
    private String email;
    private String position;
    private String principalRole;
    
    private boolean isPositionManager;
    private boolean isPositionEmployee;
    
    private boolean isRoleAdministrator;
    private boolean isRoleEmployee;
    private boolean isRoleCustomer;
    
    private boolean isActive;
    private boolean isCurrentlyLoggedIn;
    
    public static class UserDisplayDTOBuilder {
        public UserDisplayDTOBuilder position(String position) {
            this.position = position;
            
            this.isPositionManager = Employee.Position.MANAGER.toString().equals(position);
            this.isPositionEmployee = Employee.Position.EMPLOYEE.toString().equals(position);
            
            return this;
        }
        
        public UserDisplayDTOBuilder principalRole(String principalRole) {
            this.principalRole = principalRole;

            // Set boolean values based on principalRole
            this.isRoleAdministrator = PrincipalRole.ADMIN.name().equals(principalRole);
            this.isRoleEmployee = PrincipalRole.EMPLOYEE.name().equals(principalRole);
            this.isRoleCustomer = PrincipalRole.CUSTOMER.name().equals(principalRole);

            return this;
        }
    }
}
