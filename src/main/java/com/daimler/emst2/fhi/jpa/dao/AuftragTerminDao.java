package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragTermin;

@Repository
public interface AuftragTerminDao extends CrudRepository<AuftragTermin, String> {

    //List<AuftragTermin> findByPnr(String pnr);

    @Query("SELECT a from AuftragTermin a WHERE a.pnr = :pnr")
    public AuftragTermin findEntryByPnr(@Param("pnr") String pnr);


}
