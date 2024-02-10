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
//        Set<Role> roles = roleService.deserializeRoes(dto.getRolesSerialized());
        Administrator customer = administratorRepository.findById(dto.getId()).get();
        return Administrator.builder()
            .id(dto.getId())
            .name(dto.getName())
            .username(dto.getUsername())
            .email(dto.getEmail())
            .password(customer.getPassword())
            .isActive(dto.isActive())
            .roles(dto.getRoles())
            .build();
    }

    @Override
    public AdminDTO getDto(Administrator administrator) {
        return AdminDTO.builder()
            .name(administrator.getName())
            .username(administrator.getUsername())
            .email(administrator.getEmail())
            .isActive(administrator.isActive())
            .id(administrator.getId())
            .roles(administrator.getRoles())
            .build();
    }

}
