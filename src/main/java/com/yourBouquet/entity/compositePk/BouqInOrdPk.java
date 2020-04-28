package com.yourBouquet.entity.compositePk;

import com.yourBouquet.entity.Bouquet;
import com.yourBouquet.entity.Order;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BouqInOrdPk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "bouquet_id")
    @Setter
    private Bouquet bouquet;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @Setter
    private Order order;
}
