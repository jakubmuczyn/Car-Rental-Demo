package pl.sda.carrental.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.carrental.model.dto.CustomUserDetails;
import pl.sda.carrental.model.entity.User;
import pl.sda.carrental.model.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found")));
        return new CustomUserDetails(userOptional.get());
        ;
    }
    
    public User findByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username, username).orElseThrow(() ->
                new IllegalArgumentException("Username does not exist")
        );
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }
    
    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return Optional.empty();
        
        return Optional.of(findByUsernameOrEmail(authentication.getName()));
    }
    
    
}
