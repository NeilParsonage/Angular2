package com.daimler.emst2.fhi.jpa.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.dto.StoredProcedureResultDTO;
import com.daimler.emst2.fhi.jpa.model.AktiveCodesHist;


// statt AktiveCodesHist vielleicht eine Vorgangstabellen Entity
@Repository
public interface AuftragAenderungenDao extends JpaRepository<AktiveCodesHist, Long> {

    /*  PROCEDURE Bemerkung_Aendern (Pi_Pnr          IN     Auftrag.Pnr%TYPE
                             , Pi_Version      IN     Auftrag.Version%TYPE
                             , Pi_Bemerkung    IN     Auf_Bem.Bemerkung%TYPE
                             , Pi_User         IN     Applikation_User.User_Id%TYPE
                             , Po_Vorgang_Id      OUT Vorgaenge.Vorgang_Id%TYPE
                             , Po_Status          OUT NUMBER) IS
                             
                             */

    /* @Procedure("DIALOGMASKEN_INTERFACE_E2.BEMERKUNG_AENDERN")
    StoredProcedureResultDTO editAuftrag(String pnr,
            Long version,
            String bemerkung,
            String user);*/

    @Query(value = "Call DIALOGMASKEN_INTERFACE_E2.BEMERKUNG_AENDERN(:pnr, :version, :bemerkung, :user)",
            nativeQuery = true)
    StoredProcedureResultDTO editAuftrag(@Param("pnr") String pnr,
            @Param("version") BigDecimal version,
            @Param("bemerkung") String bemerkung,
            @Param("user") String user);
    

}
