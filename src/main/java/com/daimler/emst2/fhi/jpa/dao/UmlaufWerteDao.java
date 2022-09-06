package com.daimler.emst2.fhi.jpa.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.UmlaufWerte;

@Repository
public interface UmlaufWerteDao extends CrudRepository<UmlaufWerte, Long> {
    public Optional<UmlaufWerte> findByBandNr(Long bandNr);

    @Query("SELECT u from UmlaufWerte u WHERE u.bandNr = :bandNr")
    public UmlaufWerte findUmlaufWertForBand(@Param("bandNr") Long bandNr);

}