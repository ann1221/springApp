package com.yourBouquet.repository;

import com.yourBouquet.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order save(Order order);
    @Transactional
    void deleteByOrderId(Integer id);
}

