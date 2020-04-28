package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProdType implements Serializable {
    @Id
    @SequenceGenerator(name = "productTypeTypeIdSeq", sequenceName = "product_type_type_id_seq",  allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productTypeTypeIdSeq")
    @Column(name = "type_id" , nullable = false)
    @Getter
    @Setter
    private Integer typeId;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;
}

