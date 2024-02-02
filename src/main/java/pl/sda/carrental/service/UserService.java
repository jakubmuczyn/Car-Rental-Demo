package pl.sda.carrental.service;

import lombok.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dto.CreateUserDto;
import pl.sda.carrental.model.dto.CustomUserDetails;
import pl.sda.carrental.model.entity.User;
import pl.sda.carrental.model.repository.UserRepository;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void register(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setUsername(createUserDto.getUsername());
        user.setEmail(createUserDto.getEmail());
        // TODO: address
        user.setPassword(createUserDto.getPassword());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        
        userRepository.save(user);
    }
}