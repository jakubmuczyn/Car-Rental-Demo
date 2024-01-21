package pl.sda.carrental.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.entity.User;
import pl.sda.carrental.model.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsernameOrEmail(username);
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
    
    public User findByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username, username).orElseThrow(() ->
                new IllegalArgumentException("Username does not exist")
        );
    }
}