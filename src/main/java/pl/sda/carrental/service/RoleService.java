package pl.sda.carrental.service;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;
import pl.sda.carrental.security.PrincipalRole;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByEnum(PrincipalRole principalRole) {
       return roleRepository.findRoleByName(principalRole.name());
    }

    public Set<Role> deserializeRoes(String roleNames) {
        return Arrays.stream(roleNames.split(";")).map(roleRepository::findRoleByName).collect(Collectors.toSet());
    }
    public String serializeRoles(Set<Role> roles) {
        return roles.stream().map((Role::getName)).collect(Collectors.joining(";"));
    }
}
