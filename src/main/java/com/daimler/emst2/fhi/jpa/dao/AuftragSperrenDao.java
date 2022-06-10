package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragSperren;

@Repository
public interface AuftragSperrenDao extends CrudRepository<AuftragSperren, String> {

    @Query("SELECT a from AuftragSperren a WHERE a.pnr = :pnr")
    public List<AuftragSperren> findKabelsaetzeByPnr(@Param("pnr") String pnr);

    @Query("SELECT a from AuftragSperren a WHERE a.pnr = :pnr and a.freie <= :anzFreie and a.herkunft = 'ASP' and a.sperrtyp = 'S' and a.sperrart in ('A','T')")
    public List<AuftragSperren> findSperren(@Param("pnr") String pnr, @Param("anzFreie") Long anzFreie);

}
