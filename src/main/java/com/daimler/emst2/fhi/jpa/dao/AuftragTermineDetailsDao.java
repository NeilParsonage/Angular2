package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragTermine;
import com.daimler.emst2.fhi.jpa.model.AuftragTermineDetails;

@Repository
public interface AuftragTermineDetailsDao extends CrudRepository<AuftragTermine, String> {

    @Query("SELECT a from AuftragTermineDetails a WHERE a.pnr = :pnr")
    public List<AuftragTermineDetails> findAuftragTermineDetailsByPnr(@Param("pnr") String pnr);

}
