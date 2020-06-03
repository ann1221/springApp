package com.yourBouquet.service;

import com.yourBouquet.entity.Subscriber;
import com.yourBouquet.repository.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepo subscriberRepo;
    @Autowired
    public JavaMailSender emailSender;

    public Subscriber addSubscriber(String email){
        sendMail(email);
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);

        Subscriber sameSubscriber = subscriberRepo.getByEmail(subscriber.getEmail());
        if (sameSubscriber != null) return null;
        return subscriberRepo.save(subscriber);
    }

    public void sendMail(String email){
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Test Simple Email");
        message.setText("Hello! You've just subscribed us! Thank you! Hope you'll enjoy our messages :) ");

        // Send Message!
        this.emailSender.send(message);
    }
}
