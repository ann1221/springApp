package com.yourBouquet.repository;

import com.yourBouquet.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAll();
    Order getByOrderId(Integer id);
    Order save(Order order);
    void deleteByOrderId(Integer id);
}

