package com.yourBouquet.repository;

import com.yourBouquet.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {
    List<Delivery> findAll();
    List<Delivery> getByDeliveryId(Integer id);
    Delivery save(Delivery delivery);
    void deleteByDeliveryId(Integer id);
}
