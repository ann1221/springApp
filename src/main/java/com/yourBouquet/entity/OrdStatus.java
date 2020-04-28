package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ORDER_STATUS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrdStatus implements Serializable {
    @Id
    @SequenceGenerator(name = "orderStatusStatusIdSeq", sequenceName = "order_status_status_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderStatusStatusIdSeq")
    @Column(name = "status_id", nullable = false)
    @Getter
    @Setter
    private Integer statusId;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "description", nullable = false)
    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return "{" +
                "\"statusId\" : " + statusId +
                ", \"name\" : " + name + '\"' +
                ", \"description\" : \"" + description + "\"" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdStatus that = (OrdStatus) o;
        return statusId == that.statusId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, name, description);
    }
}
