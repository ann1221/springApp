package com.yourBouquet.entity;

import com.yourBouquet.entity.compositePk.BouqInOrdPk;
import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BOUQUET_IN_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BouqInOrd implements Serializable {
    @EmbeddedId
    @Setter
    private BouqInOrdPk bouqInOrdPk;

    @ManyToOne
    @JoinColumn(name = "bouquet_id",insertable = false, updatable = false)
    @Getter
    private Bouquet bouquet;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @Getter
    private Order order;

    @Column(name = "amount")
    @Getter
    @Setter
    private Integer amount;

    @Override
    public String toString() {
        return "{" +
                "\"bouquet\" : " + bouquet +
                ", \"order\" : " + order +
                ", \"amount\" : " + amount +
                '}';
    }
}
