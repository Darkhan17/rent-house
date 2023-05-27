package kz.kbtu.renthouse.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.kbtu.renthouse.repository.entity.Role;
import kz.kbtu.renthouse.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String email;
    private Role role;
    private boolean isActive;

    @JsonIgnore
    private String password;

    public static UserDetailsImpl build(User user) {
        /*
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

         */

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getPassword(),
                new ArrayList<>());
    }


    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return null;
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
        return isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
