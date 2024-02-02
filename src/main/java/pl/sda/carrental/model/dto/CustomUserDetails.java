package pl.sda.carrental.model.dto;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    
    private final User user;
}
