package pl.sda.carrental.model.dataTransfer.mappers;

import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dataTransfer.UserDisplayDto;
import pl.sda.carrental.model.entity.userEntities.User;
import pl.sda.carrental.service.UserService;

import java.util.List;

@Service
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public <T extends User> UserDisplayDto getUserDisplayDto(T user){
       return UserDisplayDto.builder()
               .name(user.getName())
               .email(user.getEmail())
               .username(user.getUsername())
               .id(user.getId())
               .isActive(user.isActive())
               .principalRole(userService.getPrincipalRole(user).getName())
               .build();
    }

    public <T extends User> List<UserDisplayDto> getUserDisplayDtos(List<T> users) {
        return users.stream().map(this::getUserDisplayDto).toList();
    }

}
