package pl.sda.carrental.model.entity;

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
@Table(name = "Clients")
public class Client extends User {
}
