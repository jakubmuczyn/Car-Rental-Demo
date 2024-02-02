package pl.sda.carrental.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Customers")
public class Customer extends User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
