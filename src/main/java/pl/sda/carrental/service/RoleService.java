package pl.sda.carrental.service;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;
import pl.sda.carrental.security.PrincipalRole;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByEnum(PrincipalRole principalRole) {
       return roleRepository.findRoleByName(principalRole.name());
    }
}
