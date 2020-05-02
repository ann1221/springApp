package com.yourBouquet.repository;

import com.yourBouquet.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAll();
    Purchase getByPurchId(Integer id);
    Purchase save(Purchase purchase);
    void deleteByPurchId(Integer id);

}
