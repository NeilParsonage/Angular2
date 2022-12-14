package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragHeberhaus;

@Repository
public interface AuftragHeberhausDao extends CrudRepository<AuftragHeberhaus, String> {

    @Query("SELECT a from AuftragHeberhaus a WHERE a.pnr = :pnr")
    public AuftragHeberhaus findAuftragHeberhausByPnr(@Param("pnr") String pnr);

}
