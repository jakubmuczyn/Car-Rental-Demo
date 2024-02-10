package pl.sda.carrental.model.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDivisionDTO {
    private long division_id;
    private String city;
    private String address;
    private String state;
    private EmployeeDTO manager;

    public String toString() {
        return "Division_" + (division_id < 10 ? "0" + division_id : String.valueOf(division_id));
    }

}
