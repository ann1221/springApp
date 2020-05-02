package com.yourBouquet.repository;

import com.yourBouquet.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourierRepo extends JpaRepository<Courier, Integer> {
    List<Courier> findAll();
    Courier getByCourierId(Integer id);
    Courier save(Courier courier);
    //Courier findFirstByPhone(Long phone);
    void deleteByCourierId(Integer id);
}
