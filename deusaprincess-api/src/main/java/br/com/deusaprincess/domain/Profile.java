package br.com.deusaprincess.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_profile")
public class Profile implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = -6097727149841731979L;

    @Id
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_profile_functionality",
            joinColumns = @JoinColumn(name = "uuid_profile"),
            inverseJoinColumns = @JoinColumn(name = "uuid_functionality"))
    private Set<Functionality> functionalities;

    @Override
    public String getAuthority() {
        return name;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        functionalities.forEach(functionality -> {
            authorities.add(new SimpleGrantedAuthority(functionality.getEndpoint() + ":" + functionality.getMethod()));
        });
        return authorities;
    }
}

