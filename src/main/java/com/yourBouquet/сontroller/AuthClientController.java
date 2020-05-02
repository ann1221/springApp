package com.yourBouquet.сontroller;

import com.yourBouquet.entity.Client;
import com.yourBouquet.repository.ClientRepo;
import com.yourBouquet.service.CatalogService;
import com.yourBouquet.service.ClientAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RequestMapping(value = "/user")
public class AuthClientController {
    @Autowired
    ClientAuthService clientAuthService;

    @GetMapping(value = "/info")
    public ResponseEntity<String> getUserInfo(){
        String result = clientAuthService.getAuthClient();
        return result != null && result.length()>0 ? new ResponseEntity<String>(result, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
