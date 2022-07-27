package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragZeit;

@Repository
public interface AuftragZeitDao extends CrudRepository<AuftragZeit, String> {

    List<AuftragZeit> findByBereich(String name);

    @Query("SELECT a from AuftragZeit a WHERE a.bereich = :bereich")
    public AuftragZeit findEntryByBereich(@Param("bereich") String bereich);


}
