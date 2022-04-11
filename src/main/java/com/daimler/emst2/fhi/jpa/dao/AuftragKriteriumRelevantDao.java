package com.daimler.emst2.fhi.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.KriteriumRelevant;

@Repository
public interface AuftragKriteriumRelevantDao extends PagingAndSortingRepository<KriteriumRelevant, String> {
    @Query("SELECT a from KriteriumRelevant a WHERE a.pnr = :pnr")
    public List<KriteriumRelevant> findAuftragKriteriumRelevantByPnr(@Param("pnr") String pnr);
}
