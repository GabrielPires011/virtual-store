package br.com.deusaprincess.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    private Long cpf;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_profile",
            joinColumns = @JoinColumn(name = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "uuid_profile"))
    private Set<Profile> profiles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_address",
            joinColumns = @JoinColumn(name = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "uuid_address"))
    private Set<Address> addresses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        profiles.forEach(profile -> {
            authorities.addAll(profile.getAuthorities());
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return active;
    }
}

