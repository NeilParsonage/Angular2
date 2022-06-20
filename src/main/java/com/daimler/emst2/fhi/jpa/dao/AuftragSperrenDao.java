package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragSperren;
import com.daimler.emst2.fhi.jpa.model.IAuftragSperrenForBereich;

@Repository
public interface AuftragSperrenDao extends CrudRepository<AuftragSperren, String> {

    @Query("SELECT a from AuftragSperren a WHERE a.pnr = :pnr")
    public List<AuftragSperren> findKabelsaetzeByPnr(@Param("pnr") String pnr);

    @Query("SELECT a from AuftragSperren a WHERE a.pnr = :pnr and a.freie <= :anzFreie and a.herkunft = 'ASP' and a.sperrtyp = 'S' and a.sperrart in ('A','T') and a.relevant = true ")
    public List<AuftragSperren> findSperren(@Param("pnr") String pnr, @Param("anzFreie") Long anzFreie);

    // AND (Sperrart != 'R' OR (Sperrart = 'R' AND NVL (Vf, '##') != 'VF'))
    @Query("SELECT a.bereichFHI AS bereich, COUNT(a.bereichFHI) AS anzahlForBereich from AuftragSperren a WHERE a.pnr = :pnr and a.sperrtyp = 'S' and a.relevant = true  and ( a.sperrart !=  'R' or ( a.sperrart =  'R'  and coalesce(a.vf, '##') != 'VF')) GROUP BY a.bereichFHI")
    public List<IAuftragSperrenForBereich> findSperrenFuerBereich(@Param("pnr") String pnr);

}
