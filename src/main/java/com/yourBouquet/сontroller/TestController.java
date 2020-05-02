package com.yourBouquet.сontroller;

import com.yourBouquet.repository.*;
import com.yourBouquet.entity.*;
import com.yourBouquet.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    ProductTypeRepo productTypeRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CourierRepo courierRepo;
    @Autowired
    DealerRepo dealerRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ProdInBouqRepo prodInBouqRepo;
    @Autowired
    BouquetRepo bouquetRepo;

    @GetMapping(path="/types")
    public @ResponseBody List<ProdType> getAllProdTypes() {
        return productTypeRepo.findAll();
    }

    @GetMapping(path="/types/{type_id}")
    public @ResponseBody
    ProdType findByTypeId(@PathVariable Integer type_id) {
        return productTypeRepo.getByTypeId(type_id);
    }

    @GetMapping(path = "/products/add")
    public @ResponseBody
    Product addProd() {
        Product product = new Product();
        product.setName("Гербера Mix");
        product.setPrice(80);
        product.setProdType(new ProdType(2, null));
        return productRepo.save(product);
    }

    @GetMapping(path = "/products/{prodId}/amount")
    public @ResponseBody Integer getAmountProdInStock(@PathVariable Integer prodId){
        return productRepo.getAmountProdInStockNative(prodId);
    }

    @GetMapping(path = "/products/like", params = {"name"})
    public @ResponseBody List<Product> getSameNameProducts(@RequestParam String name){
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    @GetMapping(path = "/couriers/add")
    public @ResponseBody
    Courier addCourier(){
        Courier courier = new Courier();
        courier.setFname("Боровик");
        courier.setSname("Степан");
        courier.setLname("Степанович");
        courier.setPhone(88005553434L);
        return courierRepo.save(courier);
    }

    @GetMapping(path = "/couriers/{courierId}/delete")
    public void deleteCourier(@PathVariable Integer courierId){
        courierRepo.deleteByCourierId(courierId);
    }

    @GetMapping(path = "/dealers")
    public @ResponseBody List<Dealer> getAll(){
        return dealerRepo.findAll();
    }

//    @GetMapping(path = "/clients/add")
//    public @ResponseBody
//    Client addClient(){
//        Client client = new Client();
//        client.setFname("Rf");
//        client.setSname("CD");
//
//        client.setPhone(900088L);
//        client.setAddress("adfasdfasdf");
//        client.setEmail("adfasdfasdf");
//         clients =  clientRepo.getByPhone(client.getPhone());
//        if (clients.size()>0) return null;
//        return clientRepo.save(client);
//    }

    @GetMapping(path = "/bouquets/{bouquetId}")
    public @ResponseBody List<ProdInBouq> getProdsInBouq(@PathVariable Integer bouquetId){
        Bouquet bouquet = new Bouquet(bouquetId,null, null, null,null, null);
        return prodInBouqRepo.getByBouquet(bouquet);
    }

    @GetMapping(path = "/bouquets/{bouquetId}/avail")
    public @ResponseBody Integer getBouquetsInIds(@PathVariable Integer bouquetId){
        return bouquetRepo.getAmountAvailableNative(bouquetId);
    }

    @GetMapping (path = "/bouquets/like")
    public @ResponseBody List<Bouquet> getBouquetsByProdName(@RequestParam String prodName){
        return bouquetRepo.findAllByProdNameAsc(prodName);
    }

    @GetMapping (path = "/bouquets/slice")
    public @ResponseBody List<Bouquet> getSliceOfBouquets(@RequestParam Integer start, @RequestParam Integer capacity ){
        return bouquetRepo.findAll(PageRequest.of(start, capacity, Sort.by("name"))).getContent();
    }

    @Autowired
    CatalogService catalogService;

    @GetMapping (path = "/catalog")
    public @ResponseBody String getCatalog(){
        return catalogService.getCatalog();
    }

    @GetMapping (path = "/catalog/{start}/{capacity}")
    public @ResponseBody String getCatatlog(@PathVariable Integer start, @PathVariable Integer capacity){
        return catalogService.getCatalog(start, capacity);
    }

    @GetMapping (path = "/catalog/byname")
    public @ResponseBody String getCatatlog(@RequestParam String prodName){
        return catalogService.getCatalogByProdName(prodName);
    }

    @GetMapping (path = "/catalog/byname/{start}/{maxCount}")
    public @ResponseBody String getCatatlog(@RequestParam String prodName, @PathVariable Integer start, @PathVariable Integer maxCount){
        return catalogService.getCatalogByProdName(prodName, start, maxCount);
    }
}
