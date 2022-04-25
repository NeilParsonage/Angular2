package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.daimler.emst2.fhi.jpa.model.AktiveCodesHist;

public interface AktiveCodesHistDao extends PagingAndSortingRepository<AktiveCodesHist, Long> {

    @Modifying
    @Query("delete from AktiveCodesHist a where a.aufPnr = :pnr and a.typ like :bereichLikePattern")
    int deleteOldCodesForBereich(@Param("pnr") String pnr, @Param("bereichLikePattern") String bereichLikePattern);

}
