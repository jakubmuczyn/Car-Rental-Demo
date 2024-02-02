package pl.sda.carrental.model.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sda.carrental.model.entity.User;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    
    private final User user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    public String getEmail() {
        return user.getEmail();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        // TODO
        // if (user.getExpiryDate() < new Date()) { return false; }
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        // TODO
        // if (user.isBanned()) { return false; }
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO
        // if (user.getPasswordExpieyDate() < new Date()) { return false; }
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        // TODO
        // if (user.isEnabled()) { return false; }
        return true;
    }
}
