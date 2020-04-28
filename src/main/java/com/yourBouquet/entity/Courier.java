package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Courier")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Courier implements Serializable {
    @Id
    @SequenceGenerator(name = "courierCourierIdSeq", sequenceName = "courier_courier_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courierCourierIdSeq")
    @Column(name = "courier_id", nullable = false)
    @Getter
    @Setter
    private Integer courierId;

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

    @Override
    public String toString()
    {
        return "{" +
                "\"courier_id\" : " + courierId +
                ", \"fname\" : \"" + fname + '\"' +
                ", \"sname\" : \"" + sname + '\"' +
                ", \"lname\" : \"" + lname + '\"' +
                ", \"phone_number\" : " + phone +
                '}';
    }
}
