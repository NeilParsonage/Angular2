package com.daimler.emst2.fhi.jpa.dao;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;

public interface AktiveRestriktionDao extends PagingAndSortingRepository<AktiveRestriktion, String> {

    public static final int MAX_ANZAHL_AKTIVE_RESTRIKTIONEN_ALL = 5000;

    // @Query(name = "select count(*) from AktiveRestriktion where updDate > :updDate")
    int updDateGreaterThan(@Param("updDate") Date letzteBekannteAenderung);

}
