package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragCodes;

@Repository
public interface AuftragCodesDao extends CrudRepository<Auftraege, String> {

    @Query("SELECT a from AuftragCodes a WHERE a.pnr = :pnr order by relevant, code")
    public List<AuftragCodes> findCodesByPnr(@Param("pnr") String pnr);

    @Query("SELECT a from AuftragCodes a WHERE a.pnr = :pnr and bereich='ALL'")
    public List<AuftragCodes> findAllCodesByPnr(@Param("pnr") String pnr);

    @Query("SELECT a from AuftragCodes a WHERE a.pnr = :pnr and bereich in ('FHI', 'BAND')")
    public List<AuftragCodes> findRelevanteCodesByPnr(@Param("pnr") String pnr);
}
