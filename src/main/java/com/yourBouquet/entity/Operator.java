package com.yourBouquet.entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OPERATOR")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Operator implements Serializable {
    @Id
    @SequenceGenerator(name = "operatorOperatorIdSeq", sequenceName = "operator_operator_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operatorOperatorIdSeq")
    @Column(name = "operator_id", nullable = false)
    @Getter
    @Setter
    private Integer operatorId;

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

    @Column(name = "description", nullable = false)
    @Getter
    @Setter
    private String description;

    @Override
    public String toString()
    {
        return "{" +
                "\"operator_id\" : " + operatorId +
                ", \"fname\" : \"" + fname + '\"' +
                ", \"sname\" : \"" + sname + '\"' +
                ", \"lname\" : \"" + lname + '\"' +
                ", \"phone_number\" : " + phone +
                ", \"address\" : \"" + address + '\"' +
                ", \"description\" : \"" + description + '\"' +
                '}';
    }
}
