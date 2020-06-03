package com.yourBouquet.service;

import com.yourBouquet.repository.ClientRepo;
import com.yourBouquet.entity.Client;
import com.yourBouquet.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ClientAuthService implements UserDetailsService {
    @Autowired
    ClientRepo clientRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepo.getByEmail(email);
        if (client == null) throw new UsernameNotFoundException("User not found");
        return client;
    }

    public boolean saveAuthClient(Client client) {
        Client dbClient = clientRepo.getByEmail(client.getEmail());
        if (dbClient != null) return false;

        client.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepo.save(client);
        return true;
    }

    public boolean deleteAuthUser(Client client) {
        Client dbClient = clientRepo.getByEmail(client.getEmail());
        if (client == null) return false;
        clientRepo.deleteById(dbClient.getClientId());
        return true;
    }

    public String getAuthClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientRepo.getByEmail(authentication.getName()).toString();
    }
}
