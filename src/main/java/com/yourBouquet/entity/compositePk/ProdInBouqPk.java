package com.yourBouquet.entity.compositePk;

import com.yourBouquet.entity.Bouquet;
import com.yourBouquet.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdInBouqPk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "bouquet_id")
    @Setter
    private Bouquet bouquet;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    @Setter
    private Product product;
}
