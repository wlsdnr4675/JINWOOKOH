package shop.jinwookoh.api.user.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.user.domain.UserVo;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final long userid;
    private final String username;
    private final String email;
    private final String name;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetailsImpl build(UserVo user) {
        List<GrantedAuthority> authories = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
        return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getName(), authories);
    }
}