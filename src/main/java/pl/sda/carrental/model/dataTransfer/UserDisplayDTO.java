package pl.sda.carrental.model.dataTransfer;


import lombok.Builder;
import lombok.Data;
import pl.sda.carrental.security.PrincipalRole;

@Builder
@Data
public class UserDisplayDTO {
    private String username;
    private long id;
    private String name;
    private String email;
    private String principalRole;
    private boolean isAdministrator;
    private boolean isEmployee;
    private boolean isCustomer;
    private boolean isActive;
    private boolean isCurrentlyLoggedIn;
      public static class UserDisplayDTOBuilder {
        public UserDisplayDTOBuilder principalRole(String principalRole) {
            this.principalRole = principalRole;

            // Set boolean values based on principalRole
            this.isAdministrator = PrincipalRole.ADMIN.name().equals(principalRole);
            this.isEmployee = PrincipalRole.EMPLOYEE.name().equals(principalRole);
            this.isCustomer = PrincipalRole.CUSTOMER.name().equals(principalRole);

            return this;
        }
    }
}
