package com.yourBouquet.repository;

import com.yourBouquet.entity.BouqInOrd;

import com.yourBouquet.entity.compositePk.BouqInOrdPk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface BouqInOrdRepo extends JpaRepository<BouqInOrd, BouqInOrdPk> {
    BouqInOrd getByBouqInOrdPk(BouqInOrdPk bouqInOrdPk);
    BouqInOrd save(BouqInOrd bouqInOrd);
    void deleteByBouqInOrdPk(BouqInOrdPk bouqInOrdPk);
}
