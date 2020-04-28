package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="PURCHASE")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Purchase implements Serializable {
    @Id
    @SequenceGenerator(name = "purchasePurchIdSeq", sequenceName = "purchase_purch_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchasePurchIdSeq")
    @Column(name = "purch_id", nullable = false)
    @Getter
    @Setter
    private Integer purchId;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    @Getter
    @Setter
    private Dealer dealer;

    @Column(name = "purch_date")
    @Getter
    @Setter
    private Date purchDate;

    @Override
    public String toString() {
        return "{" +
                "\"purch_id\" : " + purchId +
                ", \"dealer\" : " + dealer +
                ", \"purch_date\" : " + purchDate +
                '}';
    }
}
