package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.AdminDTO;
import pl.sda.carrental.model.entity.userEntities.Administrator;
import pl.sda.carrental.model.repository.userRepositories.AdministratorRepository;

@Service
public class AdministratorMapper implements UserDtoMapper<Administrator, AdminDTO> {
    private final AdministratorRepository administratorRepository;
    
    public AdministratorMapper(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    
    @Override
    public Administrator getUserClass(AdminDTO dto) {
        Administrator customer = administratorRepository.findById(dto.getId()).get();
        return Administrator.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .password(customer.getPassword())
                .isActive(dto.isActive())
                .build();
    }
    
    @Override
    public AdminDTO getDto(Administrator userClass) {
        return AdminDTO.builder()
                .id(userClass.getId())
                .username(userClass.getUsername())
                .name(userClass.getName())
                .email(userClass.getEmail())
                .role(userClass.getRole())
                .isActive(userClass.isActive())
                .build();
    }
}
