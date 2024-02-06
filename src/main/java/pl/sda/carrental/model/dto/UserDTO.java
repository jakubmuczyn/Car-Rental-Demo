package pl.sda.carrental.model.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO  {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private String rolesSemicolonSeparated;
}
