package com.yourBouquet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yourBouquet.dao.DealerRepo;
import com.yourBouquet.dao.ProdInPurchRepo;
import com.yourBouquet.dao.PurchaseRepo;
import com.yourBouquet.entity.Dealer;
import com.yourBouquet.entity.ProdInPurch;
import com.yourBouquet.entity.Product;
import com.yourBouquet.entity.Purchase;
import com.yourBouquet.entity.compositePk.ProdInPurchPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {
    @Autowired
    DealerRepo dealerRepo;
    @Autowired
    PurchaseRepo purchaseRepo;
    @Autowired
    ProdInPurchRepo prodInPurchRepo;

    public Boolean addPurchase(String fname, String  sname, String lname, Long phone,
                               String address, String descr, Date purchDate, String purchList){
        Dealer dealer = new Dealer();
        dealer.setFname(fname);
        dealer.setSname(sname);
        dealer.setLname(lname);
        dealer.setPhone(phone);
        dealer.setAddress(address);
        dealer.setDescription(descr);

        List<Dealer> sameDealers =
                dealerRepo.getDealersByFnameAndSnameAndPhoneAndAddress(
                        dealer.getFname(), dealer.getSname(), dealer.getPhone(), dealer.getAddress());

        if (sameDealers.size() > 0) dealer = sameDealers.get(0);
        else dealer = dealerRepo.save(dealer);

        Purchase purchase = new Purchase();
        purchase.setPurchDate(purchDate);
        purchase.setDealer(dealer);
        purchase = purchaseRepo.save(purchase);
        return addPurchase(purchase, purchList);
    }

    public Boolean addPurchase(Purchase purchase, String purchList){
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory factory = TypeFactory.defaultInstance();
        MapType mapType = factory.constructMapType(HashMap.class, String.class, String.class);
        CollectionLikeType listType = factory.constructCollectionLikeType(List.class, String.class);

        boolean isAllProductsAdded = true;
        try {
            Map<String, String> products  = mapper.readValue(purchList, mapType);
            Integer prodId;
            List<String> amountAndPrice = new ArrayList<>(2);

            for (Map.Entry<String, String> entry : products.entrySet()){
                prodId = Integer.valueOf(entry.getKey());
                amountAndPrice = mapper.readValue(entry.getValue(), listType);

                ProdInPurch prodInPurch = new ProdInPurch();
                Product dummyProduct = new Product();
                dummyProduct.setProdId(prodId);
                ProdInPurchPk pk = new ProdInPurchPk(dummyProduct, purchase);
                prodInPurch.setProdInPurchPk(pk);
                prodInPurch.setAmount(Integer.valueOf(amountAndPrice.get(0)));
                prodInPurch.setPrice(Integer.valueOf(amountAndPrice.get(1)));
                try{
                    prodInPurchRepo.save(prodInPurch);
                } catch (Exception e) {
                    e.printStackTrace();
                    isAllProductsAdded = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            isAllProductsAdded = false;
        }
        return isAllProductsAdded;
    }
}
