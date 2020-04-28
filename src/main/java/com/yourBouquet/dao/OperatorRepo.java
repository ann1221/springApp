package com.yourBouquet.dao;

import com.yourBouquet.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepo extends JpaRepository<Operator, Integer> {
    List<Operator> findAll();
    Operator getByOperatorId(Integer id);
    Operator save(Operator operator);
    void deleteByOperatorId(Integer id);
}
