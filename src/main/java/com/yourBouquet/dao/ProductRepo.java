package com.yourBouquet.dao;

import com.yourBouquet.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findByNameContainingIgnoreCase(String name);

    Product save(Product product);

    @Query(value = "select summ1-summ2 from " +
            "(select coalesce(sum(amount),0) summ1 from ann1221.prod_in_purch pip " +
            "where prod_id = ?1) t1, " +
            "(select coalesce(sum(bio.amount*pib.amount),0) summ2 " +
            "from ann1221.bouquet b, ann1221.bouquet_in_order bio, ann1221.product_in_bouquet pib, ann1221.product p " +
            "where bio.bouquet_id=b.bouquet_id and b.bouquet_id=pib.bouquet_id " +
            "and pib.prod_id=p.prod_id and p.prod_id = ?1 ) t2 ",
            nativeQuery = true)
    Integer getAmountProdInStockNative(Integer id);
}
