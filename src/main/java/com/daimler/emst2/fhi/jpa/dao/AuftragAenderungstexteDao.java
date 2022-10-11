package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragAenderungstexte;

@Repository
public interface AuftragAenderungstexteDao extends CrudRepository<AuftragAenderungstexte, String> {

    @Query("SELECT a from AuftragAenderungstexte a WHERE a.pnr = :pnr ORDER by a.reihenfolge")
    public List<AuftragAenderungstexte> findAuftragAenderungstexteByPnr(@Param("pnr") String pnr);

}
