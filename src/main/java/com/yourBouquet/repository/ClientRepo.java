package com.yourBouquet.repository;

import com.yourBouquet.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    List<Client> findAll();

    Client getByEmail(String email);
    Client getByPhone(Long phone);

    Client save(Client client);

    @Transactional
    void deleteByClientId(Integer id);
}
