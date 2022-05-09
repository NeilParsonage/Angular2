package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AktiveRestriktionHist;

@Repository
public interface AktiveRestriktionHistDao extends CrudRepository<AktiveRestriktionHist, Long> {
    @Modifying
    @Query("delete from AktiveRestriktionHist a where a.aufPnr = :pnr and bereich like :bereich")
    int deleteOldRestriktionenForBereich(@Param("pnr") String pnr, @Param("bereich") String bereich);
}
