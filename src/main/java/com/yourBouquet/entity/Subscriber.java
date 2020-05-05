package com.yourBouquet.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "SUBSCRIBER")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Subscriber implements Serializable{

    @Id
    @Column(name = "email", nullable = false)
    @Getter
    @Setter
    private String email;

    @Override
    public String toString() {
        return "{" +
                "\"email\" : \"" + email + '\"' +
                '}';
    }
}
