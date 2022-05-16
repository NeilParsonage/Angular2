package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;

@Repository
public interface AuftraegeDao extends CrudRepository<Auftraege, String> {

    @Query("SELECT a from Auftraege a WHERE a.lfdNrGes between (:lfdNrGes-10) and (:lfdNrGes+10) order by a.lfdNrGes")
    public List<Auftraege> findListAuftraegebyLfdNrGes(@Param("lfdNrGes") int lfdNrGes);

    @Query("SELECT a from Auftraege a WHERE a.lfdNrGes = :lfdNrGes")
    public Auftraege findbyLfdNrGes(@Param("lfdNrGes") long lfdNrGes);
    
    @Query("SELECT a from Auftraege a WHERE a.lfdNrLmt = :lfdNrLmt")
    public Auftraege findbyLfdNrLmt(@Param("lfdNrLmt") long lfdNrLmt);

    @Query("SELECT a from Auftraege a WHERE a.lfdNrFhi = :lfdNrFhi")
    public Auftraege findbyLfdNrFhi(@Param("lfdNrFhi") long lfdNrFhi);

    @Query("SELECT a from Auftraege a WHERE a.pnr like :pnr and a.version = :version")
    public Auftraege findbyPnrAndVersion(@Param("pnr") String pnr, @Param("version") long version);

    @Query(nativeQuery = true,
            value = "select PNR from AUFTRAG where PNR = :pnr and VERSION = :version for update nowait")
    public String lockAuftragForUpdate(@Param("pnr") String pnr, @Param("version") Long version);

}
