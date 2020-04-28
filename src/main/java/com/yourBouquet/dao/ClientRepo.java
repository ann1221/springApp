package com.yourBouquet.dao;

import com.yourBouquet.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    List<Client> findAll();

    Client getByClientId(Integer id);

    List<Client> getByFnameAndSnameAndLnameAndPhoneAndAddressAndEmail(String fname, String sname, String lname,
                                                                      Long phone, String address, String email);

    Client save(Client client);
}
