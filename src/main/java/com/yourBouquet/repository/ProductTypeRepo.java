package com.yourBouquet.repository;

import com.yourBouquet.entity.ProdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProdType, Integer> {
    List<ProdType> findAll();
    ProdType getByTypeId(Integer type_id);
}
