package pl.sda.carrental.model.repository.dto;

import lombok.Data;
import pl.sda.carrental.model.entity.Address;

@Data
public class DivisionDto {
    private Long division_id;
    private Address address;
}
