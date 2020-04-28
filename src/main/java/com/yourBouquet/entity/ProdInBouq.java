package com.yourBouquet.entity;

import com.yourBouquet.entity.compositePk.ProdInBouqPk;
import lombok.*;

import javax.persistence.*;

//@NamedQueries(
//    @NamedQuery(
//        name = "amountProdInBouquetQuery",
//        query = "SELECT amount FROM ProductInBouquet where bouquet.bouquet_id=:bouquet_id and product.prod_id=:prod_id"
//    )
//)

@Entity
@Table(name="PRODUCT_IN_BOUQUET")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProdInBouq {

    @EmbeddedId
    @Setter
    private ProdInBouqPk prodInBouqPk;

    @ManyToOne
    @JoinColumn(name = "bouquet_id",insertable = false, updatable = false)
    private Bouquet bouquet;

    @ManyToOne
    @JoinColumn(name = "prod_id", insertable = false, updatable = false)
    @Getter
    private Product product;

    @Column(name = "amount", nullable = false)
    @Getter
    @Setter
    private Integer amount;

    @Override
    public String toString() {
        return "{" +
                "\"product\" : " + product +
                ", \"amount\" : " + amount +
                '}';
    }


}
