package com.yourBouquet.dao;

import com.yourBouquet.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DealerRepo extends JpaRepository<Dealer, Integer> {
    List<Dealer> findAll();
    List<Dealer> getDealersByFnameAndSnameAndPhoneAndAddress(String fname, String sname, Long phone, String addr);
    Dealer save(Dealer dealer);
}
