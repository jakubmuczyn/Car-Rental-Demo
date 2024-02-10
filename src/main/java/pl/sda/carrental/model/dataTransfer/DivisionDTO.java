package pl.sda.carrental.model.dataTransfer;

import lombok.Data;
import pl.sda.carrental.model.entity.Address;

@Data
public class DivisionDTO {
    private Long division_id;
    private Address address;
}
