package com.yourBouquet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yourBouquet.dao.BouquetRepo;
import com.yourBouquet.dao.ProdInBouqRepo;
import com.yourBouquet.entity.Bouquet;
import com.yourBouquet.entity.ProdInBouq;
import com.yourBouquet.entity.Product;
import com.yourBouquet.entity.compositePk.ProdInBouqPk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CatalogService {
    @Autowired
    BouquetRepo bouquetRepo;
    @Autowired
    ProdInBouqRepo prodInBouqRepo;

    public String getCatalog(){
        List<Bouquet> bouquets = bouquetRepo.findAllByOrderByNameAsc();
        return makeCatalog(bouquets);
    }

    public String getCatalog(Integer start, Integer maxCount){
        Pageable pageable = PageRequest.of(start, maxCount, Sort.by("name"));
            List<Bouquet> bouquets = bouquetRepo.findAllByOrderByNameAsc(pageable).getContent();
            return makeCatalog(bouquets);
    }

    public String getCatalogByIds(String listIdsJson){
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory factory = TypeFactory.defaultInstance();
        CollectionLikeType listType = factory.constructCollectionLikeType(List.class, Integer.class);
        try{
            List<Integer> listIds = mapper.readValue(listIdsJson, listType);
            List<Bouquet> bouquets = bouquetRepo.getBouquetsByBouquetIdInOrderByNameAsc(listIds);
            return makeCatalog(bouquets);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getCatalogByProdName(String prodName){
        List<Bouquet> bouquets = bouquetRepo.findAllByProdNameAsc(prodName);
        return makeCatalog(bouquets);
    }

    public String getCatalogByProdName(String prodName, Integer start, Integer maxCount){
        List<Bouquet> bouquets = bouquetRepo.findAllByProdNameAsc(prodName, start, maxCount);
        return makeCatalog(bouquets);
    }

    public String getBouquet(Integer bouquetId){
        Bouquet bouquet = bouquetRepo.getByBouquetId(bouquetId);
        List<Bouquet> bouquets = new ArrayList<>();
        bouquets.add(bouquet);
        return makeCatalog(bouquets);
    }

    private String makeCatalog(List<Bouquet> bouquets){
        List<String> catalog = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            StringBuffer sb;
            for (Bouquet bouquet: bouquets){
                sb = new StringBuffer(mapper.writeValueAsString(bouquet));
                sb.delete(sb.length()-1, sb.length());
                sb.append(",\"in_stock\":").append(bouquetRepo.getAmountAvailableNative(bouquet.getBouquetId()));
                sb.append("}");
                catalog.add(sb.toString());
                sb.setLength(0);
            }
            return catalog.toString();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean addBouquet(String name, String pircUrl, Integer designPrice, String descr, String plantList){
        List<Bouquet> bouquets = bouquetRepo.getBouquetsByNameIgnoreCase(name);
        if (bouquets.size() > 0) return false;
        Bouquet bouquet = new Bouquet();
        bouquet.setName(name);
        bouquet.setPictUrl(pircUrl);
        bouquet.setDesignPrice(designPrice);
        bouquet.setDescription(descr);
        return addBouquet(bouquetRepo.save(bouquet), plantList);
    }

    public Boolean addBouquet(Bouquet bouquet, String plantList){
        ObjectMapper mapper = new ObjectMapper();
        boolean isAllBouquetsAdded = true;
        try {
            Map<String, String> map = mapper.readValue(plantList, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()){
                Integer prodId = Integer.valueOf(entry.getKey());
                Integer amount = Integer.valueOf(entry.getValue());

                ProdInBouq prodInBouq = new ProdInBouq();
                Product dummyProd = new Product();
                dummyProd.setProdId(prodId);
                ProdInBouqPk pk = new ProdInBouqPk(bouquet, dummyProd);
                prodInBouq.setProdInBouqPk(pk);
                prodInBouq.setAmount(amount);

                if (prodInBouqRepo.save(prodInBouq) == null) isAllBouquetsAdded = false;
            }
            return isAllBouquetsAdded;
        } catch (IOException e ) {
            e.printStackTrace();
            return false;
        }
    }
}
