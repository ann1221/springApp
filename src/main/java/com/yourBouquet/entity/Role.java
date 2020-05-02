package com.yourBouquet.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements GrantedAuthority {
    @Id
    @Getter
    @Setter
    private Integer roleId;

    @Getter
    @Setter
    private String name;

    public Role(Integer id, String name){
        this.roleId = id;
        this.name = name;
    }

    @Transient
    @ManyToMany
    @Getter
    @Setter
    private Set<Client> clients;

    @Override
    public String getAuthority() {
        return name;
    }
}
