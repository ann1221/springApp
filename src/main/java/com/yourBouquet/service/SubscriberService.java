package com.yourBouquet.service;

import com.yourBouquet.entity.Subscriber;
import com.yourBouquet.repository.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepo subscriberRepo;

    public Subscriber addSubscriber(String email){
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);


        Subscriber sameSubscriber = subscriberRepo.getByEmail(subscriber.getEmail());

        if (sameSubscriber != null) subscriber = sameSubscriber;
        else subscriber = subscriberRepo.save(subscriber);

        return subscriber;
    }
}
