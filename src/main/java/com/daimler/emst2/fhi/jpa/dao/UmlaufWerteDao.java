package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.UmlaufWerte;

@Repository
public interface UmlaufWerteDao extends CrudRepository<UmlaufWerte, Long> {
    public Optional<UmlaufWerte> findByBandNr(Long bandNr);

    @Query("SELECT distinct u from UmlaufWerte u WHERE u.bandNr in (1L,2L,3L)")
    public List<UmlaufWerte> findAllUmlaufWerteForAllBaender();

}