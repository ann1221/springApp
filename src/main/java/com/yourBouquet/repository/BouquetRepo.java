package com.yourBouquet.repository;

import com.yourBouquet.entity.Bouquet;

import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BouquetRepo extends JpaRepository<Bouquet, Integer> {
    List<Bouquet> findAllByOrderByNameAsc();
    Page<Bouquet> findAllByOrderByNameAsc(Pageable pageable);
    List<Bouquet> getBouquetsByBouquetIdInOrderByNameAsc(List<Integer> ids);
    List<Bouquet> getBouquetsByNameIgnoreCase(String name);

    @Query(value = "select b.* " +
            "from ann1221.bouquet b, ann1221.product_in_bouquet pib, ann1221.product p " +
            "where b.bouquet_id = pib.bouquet_id and pib.prod_id = p.prod_id and " +
            "lower(p.name) like concat('%', lower(?1), '%') order by b.name",
            nativeQuery = true)
    List<Bouquet> findAllByProdNameAsc(String prodName);

    @Query(value =
            "select t1.bouquet_id, t1.name, t1.pict_url, t1.design_price,t1.description " +
            "from ( " +
                "select row_number() OVER(ORDER BY b.name asc) -1 as row_num, b.* " +
                "from ann1221.bouquet b, ann1221.product_in_bouquet pib, ann1221.product p " +
                "where b.bouquet_id = pib.bouquet_id and pib.prod_id = p.prod_id and " +
                "lower(p.name) like concat('%', lower(?1), '%') " +
            ") t1 where row_num between ?2 and ?2+?3-1",
            nativeQuery = true)
    List<Bouquet> findAllByProdNameAsc(String prodName, Integer start, Integer maxCount);

    Bouquet getByBouquetId(Integer id);

    @Query(value =
            "select coalesce((min(coalesce(in_stock,0)/(coalesce(amount,0)))),0)  from " +
                "( select * from ann1221.product_in_bouquet pib " +
                "where pib.bouquet_id = ?1 " +
            ") prodInBouquet left join ( " +
                 "select t1.prod_id, t1.purch_summ - coalesce(t2.order_summ, 0) as in_stock " +
                 "from ( " +
                    "select prod_id,  coalesce(sum(amount),0) purch_summ " +
                    "from ann1221.prod_in_purch pip " +
                    "group by prod_id) t1 " +
                 "Left join " +
                    "(select p.prod_id, coalesce(sum(bio.amount*pib.amount),0) order_summ " +
                    "from ann1221.bouquet b, ann1221.bouquet_in_order bio, " +
                    "ann1221.product_in_bouquet pib, ann1221.product p " +
                    "where bio.bouquet_id=b.bouquet_id and b.bouquet_id=pib.bouquet_id" +
                    " and pib.prod_id=p.prod_id " +
                    "group by p.prod_id) t2 " +
                 "on t1.prod_id = t2.prod_id " +
            ") prodInStok " +
            "on prodInStok.prod_id = prodInBouquet.prod_id",
            nativeQuery = true)
    Integer getAmountAvailableNative(Integer bouquetId);

    Bouquet save(Bouquet bouquet);
}
