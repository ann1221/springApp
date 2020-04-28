package com.yourBouquet.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEALER")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Dealer implements Serializable {
    @Id
    @SequenceGenerator(name = "dealerDealerIdSeq", sequenceName = "dealer_dealer_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealerDealerIdSeq")
    @Column(name = "dealer_id", nullable = false)
    @Getter
    @Setter
    private Integer dealerId;

    @Column(name = "fname", nullable = false)
    @Getter
    @Setter
    private String fname;

    @Column(name = "sname", nullable = false)
    @Getter
    @Setter
    private String sname;

    @Column(name = "lname", nullable = false)
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

    @Column(name = "description", nullable = false)
    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return "{" +
                " \"dealer_id\" : " + dealerId +
                ", \"fName\" : \"" + fname + '\"' +
                ", \"sName\" : \"" + sname + '\"' +
                ", \"lName\" : \"" + lname + '\"' +
                ", \"phone_number\" : " + phone +
                ", \"address\" : " + address + '\"' +
                ", \"description\" : \"" + description + '\"' +
                '}';
    }
}
