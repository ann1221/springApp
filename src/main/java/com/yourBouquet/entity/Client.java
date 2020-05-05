package com.yourBouquet.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable , UserDetails {
    @Id
    @SequenceGenerator(name = "clientClientIdSeq", sequenceName = "client_client_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientClientIdSeq")
    @Column(name = "client_id", nullable = false)
    @Getter
    @Setter
    private Integer clientId;

    @Column(name = "fname")
    @Getter
    @Setter
    private String fname;

    @Column(name = "sname")
    @Getter
    @Setter
    private String sname;

    @Column(name = "lname")
    @Getter
    @Setter
    private String lname;

    @Column(name = "phone_number", nullable = false, unique = true)
    @Getter
    @Setter
    private Long phone;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "email", nullable = true, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CLIENT_ROLE",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Override
    public String toString()
    {
        return "{" +
                " \"fname\" : \"" + fname + '\"' +
                ", \"sname\" : \"" + sname + '\"' +
                ", \"lname\" : \"" + lname + '\"' +
                ", \"phone\" : " + phone +
                ", \"address\" : \"" + address + '\"' +
                ", \"email\" : \"" + email + '\"' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return true;
    }
}
