package com.yourBouquet.entity.compositePk;

import com.yourBouquet.entity.Product;
import com.yourBouquet.entity.Purchase;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdInPurchPk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "prod_id")
    @Setter
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purch_id")
    @Setter
    private Purchase purchase;
}
