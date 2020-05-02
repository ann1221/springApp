package com.yourBouquet.repository;

import com.yourBouquet.entity.ProdInPurch;
import com.yourBouquet.entity.Product;
import com.yourBouquet.entity.Purchase;
import com.yourBouquet.entity.compositePk.ProdInPurchPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdInPurchRepo extends JpaRepository<ProdInPurch, ProdInPurchPk> {
    List<ProdInPurch> getByPurchase(Purchase purchase);

    ProdInPurch getByPurchaseAndProduct(Purchase purchase, Product product);

    ProdInPurch save(ProdInPurch prodInPurch);

    void delete(ProdInPurch prodInPurch);
}
