package com.yourBouquet.dao;

import com.yourBouquet.entity.OrdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderStatusRepo extends JpaRepository<OrdStatus, Integer> {
    List<OrdStatus> findAll();
    OrdStatus getByStatusId(Integer id);
    OrdStatus save(OrdStatus ordStatus);

}
