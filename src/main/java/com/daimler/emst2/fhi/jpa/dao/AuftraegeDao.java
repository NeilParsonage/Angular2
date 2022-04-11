package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;

@Repository
public interface AuftraegeDao extends CrudRepository<Auftraege, String> {

    @Query("SELECT a from Auftraege a WHERE a.pnr = :pnr")
    public Auftraege findAuftragByPnr(@Param("pnr") String pnr);

    @Query(nativeQuery = true,
            value = "select PNR from AUFTRAG where PNR = :pnr and VERSION = :version for update nowait")
    public boolean lockAuftragForUpdate(@Param("pnr") String pnr, @Param("version") Long version);

}
