package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragLacke;

@Repository
public interface AuftragLackeDao extends CrudRepository<Auftraege, String> {

    @Query("SELECT a from AuftragLacke a WHERE a.pnr = :pnr and lackVerwendung='FHS' order by fhsReihenfolge")
    public List<AuftragLacke> findFhsLackeByPnr(@Param("pnr") String pnr);

    @Query("SELECT a from AuftragLacke a WHERE a.pnr = :pnr and lackVerwendung='RHM' ")
    public Optional<AuftragLacke> findRhmLackeByPnr(@Param("pnr") String pnr);

}
