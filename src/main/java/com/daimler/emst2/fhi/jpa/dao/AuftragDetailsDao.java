package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragDetails;

@Repository
public interface AuftragDetailsDao extends CrudRepository<AuftragDetails, String> {

    @Query("SELECT a from AuftragDetails a WHERE a.pnr = :pnr")
    public AuftragDetails findAuftragDetailsByPnr(@Param("pnr") String pnr);

}
