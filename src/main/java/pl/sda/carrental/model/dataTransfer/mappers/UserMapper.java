package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.UserDisplayDTO;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.service.UserService;

import java.util.List;

@Service
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public <T extends User> UserDisplayDTO getUserDisplayDto(T user){
       return UserDisplayDTO.builder()
               .name(user.getName())
               .email(user.getEmail())
               .username(user.getUsername())
               .id(user.getId())
               .isActive(user.isActive())
               .principalRole(userService.getPrincipalRole(user).getRoleName())
               .build();
    }

    public <T extends User> List<UserDisplayDTO> getUserDisplayDtos(List<T> users) {
        return users.stream().map(this::getUserDisplayDto).toList();
    }

}
