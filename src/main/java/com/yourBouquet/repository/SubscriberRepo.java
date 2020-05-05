package com.yourBouquet.repository;

import com.yourBouquet.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, String> {

    List<Subscriber> findAll();
    Subscriber getByEmail(String email);
    Subscriber save(Subscriber subscriber);
    @Transactional
    void deleteByEmail(String email);
}