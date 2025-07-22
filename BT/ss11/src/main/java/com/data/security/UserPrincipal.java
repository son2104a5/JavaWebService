package com.data.security;

import com.data.model.entity.Role;
import com.data.model.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isStatus();
        this.authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
            .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Có thể kiểm tra ngày hết hạn nếu muốn
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Có thể kiểm tra trạng thái khóa
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Có thể thêm kiểm tra nếu cần
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
