package com.yourBouquet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yourBouquet.repository.BouqInOrdRepo;
import com.yourBouquet.repository.ClientRepo;
import com.yourBouquet.repository.OrderRepo;
import com.yourBouquet.entity.*;
import com.yourBouquet.entity.compositePk.BouqInOrdPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    BouqInOrdRepo bouqInOrdRepo;

    public Boolean addOrder(Client client, String bouquetList){
        Client sameClient = clientRepo.getByPhone(client.getPhone());
        //Client newClient;
        if (sameClient != null ) client.setClientId(sameClient.getClientId());
        client.setRoles(Collections.singleton(new Role(3, "ROLE_GUEST")));
        client = clientRepo.save(client);

        java.util.Date utilDate = new java.util.Date();
        Date date = new Date(utilDate.getTime());

        Order order = new Order();
        order.setDate(date);
        order.setClient(client);
        Operator dummyOperator = new Operator();
        dummyOperator.setOperatorId(1);
        order.setOperator(dummyOperator);
        Delivery dummyDelivery = new Delivery();
        dummyDelivery.setDeliveryId(1);
        order.setDelivery(dummyDelivery);
        OrdStatus dummyOrdStatus = new OrdStatus();
        dummyOrdStatus.setStatusId(1);
        order.setOrdStatus(dummyOrdStatus);
        return addOrder(orderRepo.save(order), bouquetList);
    }

    public Boolean addOrder(Order order, String bouquetList ) {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory factory = TypeFactory.defaultInstance();
        MapType mapType = factory.constructMapType(HashMap.class, String.class, String.class);
        CollectionLikeType listType = factory.constructCollectionLikeType(List.class, String.class);

        Map<String, String> bouquets = null;
        Boolean isAllOrderAdded=true;
        try {
            bouquets = mapper.readValue(bouquetList, mapType);

            Integer bouquetId, amount;
            for (Map.Entry<String, String> entry : bouquets.entrySet()){
                bouquetId = Integer.valueOf(entry.getKey());
                amount = Integer.valueOf(entry.getValue());

                BouqInOrd bouqInOrd = new BouqInOrd();
                Bouquet dummyBouquet = new Bouquet();
                dummyBouquet.setBouquetId(bouquetId);
                BouqInOrdPk pk = new BouqInOrdPk(dummyBouquet, order);
                bouqInOrd.setBouqInOrdPk(pk);
                bouqInOrd.setAmount(amount);
                try {
                    bouqInOrdRepo.save(bouqInOrd);
                } catch (Exception e) {
                    isAllOrderAdded = false;
                }
            }
        } catch (IOException e) {
            isAllOrderAdded = false;
        }
        return isAllOrderAdded;
    }
}
