package pl.sda.carrental.model.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.carrental.model.entity.Address;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivisionDTOForPanel {
    private long division_id;
    private Address address;
    private List<EmployeeDTO> employees;
    private EmployeeDTO manager;

    public String toString() {
        return "Division_" + (division_id < 10 ? "0" + division_id : String.valueOf(division_id));
    }

}
