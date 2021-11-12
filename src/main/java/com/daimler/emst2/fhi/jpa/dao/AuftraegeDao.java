package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;

@Repository
public interface AuftraegeDao extends JpaRepository<Auftraege, String> {

    @Query("SELECT a from Auftraege a WHERE a.pnr = :pnr")
    public Auftraege findAuftragByPnr(@Param("pnr") String pnr);

}
