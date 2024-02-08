package pl.sda.carrental.model.dataTransfer;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean isActive;
    private String rolesSerialized;
}
