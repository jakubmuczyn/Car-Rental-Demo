package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.carrental.model.entity.userEntities.Role;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.model.repository.userRepositories.RoleRepository;

import java.util.Arrays;
import java.util.Set;

public interface UserDtoMapper<T extends User, W> {
    T getUserClass(W dto);
    W getDto(T userClass) ;
}
