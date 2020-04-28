package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="BOUQUET")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bouquet implements Serializable {
    @Id
    @SequenceGenerator(name = "bouquetBouquetId", sequenceName = "bouquet_bouquet_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bouquetBouquetId")
    @Column(name = "bouquet_id", nullable = false)
    @Getter
    @Setter
    private Integer bouquetId;

    @Column(name = "name", nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @Column(name = "design_price", nullable = false)
    @Getter
    @Setter
    private Integer designPrice;

    @Column(name = "pict_url", nullable = false)
    @Getter
    @Setter
    private String pictUrl;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bouquet", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<ProdInBouq> productsInBouquet;


    @Override
    public String toString() {
        return "{" +
                "\"bouquet_id\" : " + bouquetId +
                ", \"name\" : \"" + name + "\"" +
                ", \"pict_url\" : \"" + pictUrl + "\"" +
                ", \"design_price\" : " + designPrice +
                ", \"description\" : \"" + description + '\"' +
                ", \"product_list\" : \"" + productsInBouquet + '\"' +
                '}';
    }
}
