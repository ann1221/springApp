package com.yourBouquet.entity;

import com.yourBouquet.entity.compositePk.ProdInPurchPk;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROD_IN_PURCH")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdInPurch implements Serializable {
    @EmbeddedId
    @Setter
    private ProdInPurchPk prodInPurchPk;

    @ManyToOne
    @JoinColumn(name = "prod_id", insertable = false, updatable = false)
    @Getter
    private Bouquet product;

    @ManyToOne
    @JoinColumn(name = "purch_id", insertable = false, updatable = false)
    @Getter
    private Purchase purchase;

    @Column(name = "amount")
    @Getter
    @Setter
    private Integer amount;

    @Column(name = "price")
    @Getter
    @Setter
    private Integer price;

    @Override
    public String toString() {
        return "{" +
                "\"product\" : " + product +
                ", \"purchase\" : " + purchase +
                ", \"amount\" : " + amount +
                ", \"price\" : " + price +
                '}';
    }
}
