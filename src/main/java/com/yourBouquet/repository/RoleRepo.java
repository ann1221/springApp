package com.yourBouquet.repository;

import com.yourBouquet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Integer> {

}
