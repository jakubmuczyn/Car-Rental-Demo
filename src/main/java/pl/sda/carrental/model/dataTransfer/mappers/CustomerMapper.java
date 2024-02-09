package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.CustomerDTO;
import pl.sda.carrental.model.entity.userEntities.Customer;
import pl.sda.carrental.model.repository.userRepositories.CustomerRepository;
import pl.sda.carrental.service.RoleService;

@Service
public class CustomerMapper implements UserDtoMapper<Customer, CustomerDTO> {
    private final CustomerRepository customerRepository;
    
    public CustomerMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Override
    public Customer getUserClass(CustomerDTO dto) {
//        Set<Role> roles = roleService.deserializeRoes(dto.getRolesSerialized());
        Customer customer = customerRepository.getReferenceById(dto.getId());
        return Customer.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .name(dto.getName())
                .role(dto.getRole())
                .email(dto.getEmail())
                .password(customer.getPassword())
                .isActive(dto.isActive())
                .role(dto.getRole())
                .build();
    }
    
    @Override
    public CustomerDTO getDto(Customer userClass) {
        return CustomerDTO.builder()
                .name(userClass.getName())
                .role(userClass.getRole())
                .email(userClass.getEmail())
                .isActive(userClass.isActive())
                .id(userClass.getId())
                .role(userClass.getRole())
                .build();
    }
}
