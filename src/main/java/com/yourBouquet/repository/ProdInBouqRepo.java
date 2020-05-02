package com.yourBouquet.repository;

import com.yourBouquet.entity.Bouquet;
import com.yourBouquet.entity.ProdInBouq;
import com.yourBouquet.entity.Product;
import com.yourBouquet.entity.compositePk.ProdInBouqPk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdInBouqRepo extends JpaRepository<ProdInBouq, ProdInBouqPk> {
    List<ProdInBouq> findAll();

    ProdInBouq getByBouquetAndProduct(Bouquet bouquet, Product product);
    List<ProdInBouq> getByBouquet(Bouquet bouquet);

    ProdInBouq save(ProdInBouq prodInBouq);
}
