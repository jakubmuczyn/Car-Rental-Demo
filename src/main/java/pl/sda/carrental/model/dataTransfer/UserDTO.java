package pl.sda.carrental.model.dataTransfer;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO  {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private String rolesSerialized;
}
