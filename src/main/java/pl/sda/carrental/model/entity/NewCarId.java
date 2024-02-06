package pl.sda.carrental.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewCarId {
    public Long id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
