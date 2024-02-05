package pl.sda.carrental.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import pl.sda.carrental.model.entity.userEntities.Role;

@Builder
@Data
public class UserDisplayDto {
    private String username;
    private long id;
    private String name;
    private String email;
    private String principalRole;
}
