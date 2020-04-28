package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {
    @Id
    @SequenceGenerator(name = "productProdIdSeq", sequenceName = "product_prod_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productProdIdSeq")
    @Column(name = "prod_id", nullable = false)
    @Getter
    @Setter
    private Integer prodId;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "price")
    @Getter
    @Setter
    private  Integer price;

    @ManyToOne
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "product_type_id_fkey"))
    @Getter
    @Setter
    private ProdType prodType;

    @Override
    public String toString() {
        return "{" +
                "\"prod_id\" : " + prodId  +
                ", \"name\" : " + "\"" + name + "\"" +
                ", \"type\" : " + prodType +
                ", \"price\" : " + price +
                '}';
    }
}
