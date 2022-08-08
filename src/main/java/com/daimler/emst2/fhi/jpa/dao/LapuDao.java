package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.ICountGassenperre;
import com.daimler.emst2.fhi.jpa.model.Lapu;

@Repository
public interface LapuDao extends CrudRepository<Lapu, String> {

    @Query(nativeQuery = true,
            value = "SELECT NVL (SUBSTR (f.daten, CEIL (lapu.reihe / 2), 1), '0') AS GassenSperre FROM LAPU lapu LEFT OUTER JOIN Fhitab f ON (f.Key = 'GASSE_SP') WHERE lapu.pnr_ist = :pnr")
    public ICountGassenperre findCountGassensperre(@Param("pnr") String pnr);


    /*
    SELECT NVL (SUBSTR (Fhitab.Daten, CEIL (Lapu.Reihe / 2), 1), '0') AS Gassen_Sperre
    FROM Lapu 
       LEFT OUTER JOIN Fhitab ON (Key = 'GASSE_SP')
    WHERE Lapu.Pnr_Ist = '13617810';
    */



}
