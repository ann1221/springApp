package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CLIENT_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order implements Serializable {
    @Id
    @SequenceGenerator(name = "clientOrderOrderIdSeq", sequenceName = "client_order_order_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientOrderOrderIdSeq")
    @Column(name = "order_id", nullable = false)
    @Getter
    @Setter
    private Integer orderId;

    @Column(name = "order_date", nullable = false)
    @Getter
    @Setter
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter
    @Setter
    private Client client;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    @Getter
    @Setter
    private Operator operator;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    @Getter
    @Setter
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @Getter
    @Setter
    private OrdStatus ordStatus;

    @Override
    public String toString() {
        return "{" +
                " \"clientOrder_id\" : " + orderId +
                ", \"order_date\" : " + date +
                ", \"client\" : " + client +
                ", \"operator\" : " + operator +
                ", \"delivery\" : " + delivery +
                ", \"status\" : " + ordStatus +
                '}';
    }
}
