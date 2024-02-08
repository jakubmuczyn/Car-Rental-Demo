package pl.sda.carrental.model.dataTransfer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.carrental.model.entity.userEntities.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private Set<Role> roles;
}
