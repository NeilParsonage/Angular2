package com.daimler.emst2.fhi.jpa.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.IAuftragAllHighestSeqNr;
import com.daimler.emst2.fhi.jpa.model.ICountVorsendungen;

@Repository
public interface AuftragDao extends CrudRepository<Auftrag, String> {

    @Query("SELECT a from Auftrag a WHERE  a.ort NOT IN ('BDAB','SATG') AND a.lfdNrGes between (:lfdNrGes-10) and (:lfdNrGes+10) order by a.lfdNrGes")
    public List<Auftrag> findListAuftraegebyLfdNrGes(@Param("lfdNrGes") int lfdNrGes);

    @Query("SELECT a from Auftrag a WHERE a.lfdNrGes = :lfdNrGes")
    public Auftrag findbyLfdNrGes(@Param("lfdNrGes") long lfdNrGes);
    
    @Query("SELECT a from Auftrag a WHERE a.lfdNrLmt = :lfdNrLmt and a.bandNr= :band")
    public Auftrag findbyLfdNrLmt(@Param("lfdNrLmt") long lfdNrLmt, @Param("band") long band);

    @Query("SELECT a from Auftrag a WHERE  a.ort NOT IN ('BDAB','SATG') AND a.sendetermin IS NOT NULL AND a.lfdNrFhi = :lfdNrFhi")
    public Auftrag findbyLfdNrFhi(@Param("lfdNrFhi") long lfdNrFhi);

    @Query("SELECT a from Auftrag a WHERE a.pnr like :pnr and a.version = :version")
    public Auftrag findbyPnrAndVersion(@Param("pnr") String pnr, @Param("version") long version);

    @Query(nativeQuery = true,
            value = "select PNR from AUFTRAG where PNR = :pnr and VERSION = :version for update nowait")
    public String lockAuftragForUpdate(@Param("pnr") String pnr, @Param("version") Long version);

    @Query("SELECT MAX(a.seqnrLapu) AS maxSeqNrLapu, MAX(a.seqnrSepu) AS maxSeqNrSepu, MAX(a.seqnrNs) AS maxSeqNrNs, MAX(a.seqnrMs) AS maxSeqNrMs, MAX(a.seqnrFs) AS maxSeqNrFs  from Auftrag a")
    public IAuftragAllHighestSeqNr findMaxSeqNrn();

    //SELECT COUNT(*) AS maxVorsendungen from Auftrag INNER JOIN Ort_Reihenfolge ON Auftrag.ort = Ort_Reihenfolge.ort  and Ort_Reihenfolge.ort_Rf_Fabrik <= 210 and Auftrag.SEQNR_LAPU is not null;  

    @Query("SELECT COUNT(*) AS maxVorsendungen from Auftrag a INNER JOIN OrtReihenfolge o ON a.ort = o.ort  and o.ortRfFabrik <= :seqQuer and a.seqnrLapu != null")
    public ICountVorsendungen findMaxVorsendungen(@Param("seqQuer") BigDecimal seqQuer);

    /*
    SELECT COUNT (*)
    INTO V_Auslag_Lapu
    FROM Auftrag Auft, Ort_Reihenfolge Ort
    WHERE Ort.Ort = Auft.Ort
     AND Ort.Ort_Rf_Fabrik <= (SELECT Ort_Rf_Fabrik
                                 FROM Ort_Reihenfolge
                                WHERE Ort = 'QUER')
     AND Auft.Seqnr_Lapu IS NOT NULL;
    */



}
