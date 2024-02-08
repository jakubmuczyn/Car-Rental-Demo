package pl.sda.carrental.model.dataTransfer;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerMapper implements UserDtoMapper<Customer, CustomerDTO>{
    private final RoleService roleService;
    private final CustomerRepository customerRepository;

    public CustomerMapper(RoleService roleService, CustomerRepository customerRepository) {
        this.roleService = roleService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getUserClass(CustomerDTO dto) {
//        Set<Role> roles = roleService.deserializeRoes(dto.getRolesSerialized());
        Customer customer = customerRepository.getReferenceById(dto.getId());
        return Customer.builder()
            .id(dto.getId())
            .name(dto.getName())
            .username(dto.getUsername())
            .email(dto.getEmail())
            .password(customer.getPassword())
            .isActive(dto.isActive())
            .roles(new HashSet(dto.getRoles()))
            .build();
    }

    @Override
    public CustomerDTO getDto(Customer userClass) {
        return CustomerDTO.builder()
            .name(userClass.getName())
            .username(userClass.getUsername())
            .email(userClass.getEmail())
            .isActive(userClass.isActive())
            .id(userClass.getId())
            .roles(userClass.getRoles().stream().toList())
            .build();
    }

}
