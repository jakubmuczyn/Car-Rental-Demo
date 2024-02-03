package pl.sda.carrental.model.entity.userEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Customers")
public class Customer extends User {
}
