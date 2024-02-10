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
    
    public <T extends User> UserDisplayDTO getUserDisplayDto(T user) {
        return UserDisplayDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .position("no idea") // TODO
                .principalRole(user.getRole().getRoleName())
                .isActive(user.isActive())
                .build();
    }
    
    public <T extends User> List<UserDisplayDTO> getUserDisplayDtos(List<T> users) {
        return users.stream().map(this::getUserDisplayDto).toList();
    }
}
