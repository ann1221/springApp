package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "DELIVERY")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Delivery implements Serializable {
    @Id
    @SequenceGenerator(name = "deliveryDeliveryIdSeq", sequenceName = "delivery_delivery_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deliveryDeliveryIdSeq")
    @Column(name = "delivery_id", nullable = false)
    @Getter
    @Setter
    private Integer deliveryId;

    @Column(name = "delivery_date", nullable = false)
    @Getter
    @Setter
    private Date deliveryDate;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @Override
    public String toString()
    {
        return "{" +
                " \"delivery_id\" : " + deliveryId +
                ", \"delivery_date\" : " + deliveryDate +
                ", \"courier\" : " + courier +
                '}';
    }
}
