package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable {
    @Id
    @SequenceGenerator(name = "clientClientIdSeq", sequenceName = "client_client_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientClientIdSeq")
    @Column(name = "client_id", nullable = false)
    @Getter
    @Setter
    private Integer clientId;

    @Column(name = "fname", nullable = false)
    @Getter
    @Setter
    private String fname;

    @Column(name = "sname", nullable = false)
    @Getter
    @Setter
    private String sname;

    @Column(name = "lname")
    @Getter
    @Setter
    private String lname;

    @Column(name = "phone_number", nullable = false)
    @Getter
    @Setter
    private Long phone;

    @Column(name = "address", nullable = false)
    @Getter
    @Setter
    private String address;

    @Column(name = "email", nullable = false)
    @Getter
    @Setter
    private String email;

    @Override
    public String toString()
    {
        return "{" +
                "\"client_id\" : " + clientId +
                ", \"fname\" : \"" + fname + '\"' +
                ", \"sname\" : \"" + sname + '\"' +
                ", \"lname\" : \"" + lname + '\"' +
                ", phone_number\" : " + phone +
                ", \"address\" : \"" + address + '\"' +
                ", \"email\" : \"" + email + '\"' +
                '}';
    }
}
