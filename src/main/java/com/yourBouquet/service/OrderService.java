package com.yourBouquet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.*;

@Service
public class OrderService {
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    BouqInOrdRepo bouqInOrdRepo;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    Date date;

    public Boolean addOrder(Client client, String bouquetList){
        Client sameClient = clientRepo.getByPhone(client.getPhone());
        if (sameClient != null ) client.setClientId(sameClient.getClientId());
        client.setRoles(Collections.singleton(new Role(3, "ROLE_GUEST")));
        client = clientRepo.save(client);

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

    private Map<String,String> unpackFromJson(String bouquetListJson){
        TypeFactory factory = TypeFactory.defaultInstance();
        MapType mapType = factory.constructMapType(HashMap.class, String.class, String.class);

        try {
            return objectMapper.readValue(bouquetListJson, mapType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean addOrder(Order order, String bouquetListJson) {
        Map<String, String> bouquets = unpackFromJson(bouquetListJson);
        if (bouquets == null) return false;

        Boolean isAllOrderAdded=true;
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
                e.printStackTrace();
                isAllOrderAdded = false;
            }
        }
        return isAllOrderAdded;
    }
}
