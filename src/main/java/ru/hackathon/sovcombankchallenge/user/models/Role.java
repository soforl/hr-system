package ru.hackathon.sovcombankchallenge.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Transient
    @OneToMany(mappedBy = "ROLE", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomUser> customUsers;
    public Role() {
    }

    public Role(String name) {
        this.name = "ROLE_" + name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
