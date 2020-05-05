package com.yourBouquet.сontroller;

import com.yourBouquet.entity.Client;
import com.yourBouquet.entity.Subscriber;
import com.yourBouquet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RequestMapping(value = "/main", consumes = MediaType.ALL_VALUE)
public class MainController {
    @Autowired
    OrderService orderService;
    @Autowired
    CatalogService catalogService;
    @Autowired
    ClientAuthService clientAuthService;
    @Autowired
    SubscriberService subscriberService;
    @Autowired
    CommentService commentService;

    @PostMapping(value = "/orders/new", produces = "application/json")
    public ResponseEntity<String> addClientOrder(@RequestBody Client client, @RequestParam String bouquetList){
        Boolean result = orderService.addOrder(client, bouquetList);
        return  result? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/bouquets/{id}", produces = "application/json")
    public ResponseEntity<String> getBouquet(@PathVariable Integer id){
        String result = catalogService.getBouquet(id);
        return result != null && result.length()>0 ? new ResponseEntity<String>(result, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bouquets",
            produces = "application/json",
            params = {"bouquetsIdsJson"})
    public ResponseEntity<String> getCatalogByIds(@RequestParam String bouquetsIdsJson){
        String result = catalogService.getCatalogByIds(bouquetsIdsJson);
        return result != null && result.length()>0 ? new ResponseEntity<String>(result, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bouquets/{start}/{count}", produces = "application/json")
    public ResponseEntity<String> getCatalog(@PathVariable Integer start, @PathVariable Integer count){
        String result = catalogService.getCatalog(start, count);
        return result != null && result.length()>0 ? new ResponseEntity<String>(result, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/bouquets/byProdName/{start}/{count}",
            produces = "application/json",
            params = {"prodName"})
    public ResponseEntity<String> getCalatogByProdName(@RequestParam String prodName,
                                                       @PathVariable Integer start,
                                                       @PathVariable Integer count){
        String result = catalogService.getCatalogByProdName(prodName, start, count);
        return result != null && result.length()>0 ? new ResponseEntity<String>(result, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> addAuthClient(@RequestBody Client client) {
        return clientAuthService.saveAuthClient(client) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping(value = "/sendSimpleEmailParam",  params = {"email"})
    //localhost:8080/main/sendSimpleEmailParam?email=miss.sokolova-marya2014@yandex.ru
    public String sendSimpleEmailParam(@RequestParam String email) {
        String answer = "Email Sent!";
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Test Simple Email");
        message.setText("Hello! You've just subscribed us! Thank you! Hope you'll enjoy our messages :) ");

        // Send Message!
        this.emailSender.send(message);
        subscriberService.addSubscriber(email);
        return answer;
    }



    @GetMapping(value = "/comment",  params = {"fname", "sname", "commentText"})
    //localhost:8080/main/sendSimpleEmailParam?email=miss.sokolova-marya2014@yandex.ru
    public String Comment(@RequestParam String fname, @RequestParam String sname, @RequestParam String commentText) {
        String answer = "Comment sucsess!";
        commentService.addComment(fname, sname, commentText);
        return answer;
    }
}
