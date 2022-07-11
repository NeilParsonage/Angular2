package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftrag;

@Repository
public interface AuftragDao extends CrudRepository<Auftrag, String> {

    @Query("SELECT a from Auftrag a WHERE a.lfdNrGes between (:lfdNrGes-10) and (:lfdNrGes+10) order by a.lfdNrGes")
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

}
