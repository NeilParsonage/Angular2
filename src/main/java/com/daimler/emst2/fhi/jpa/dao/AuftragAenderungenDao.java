package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.dto.StoredProcedureResultDTO;
import com.daimler.emst2.fhi.jpa.model.AktiveCodesHist;


// statt AktiveCodesHist vielleicht eine Vorgangstabellen Entity
@Repository
public interface AuftragAenderungenDao extends JpaRepository<AktiveCodesHist, Long> {

    @Procedure(name = "DIALOGMASKEN_INTERFACE.AUFTRAG_AENDERN")
    StoredProcedureResultDTO editAuftrag(@Param("P_PNR") String pnr,
            @Param("P_VERSION") Long version,
            @Param("P_BANDNR") Long bandNr,
            @Param("P_BEMERKUNG") String bemerkung,
            @Param("P_FEHLER_STRING") String fehlerText,
            @Param("P_IGNORE_KZN") String IgnorKzn,
            @Param("p_user") String user);
    

}
