package com.daimler.emst2.fhi.jpa.dao;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftrag;


// statt AktiveCodesHist vielleicht eine Vorgangstabellen Entity
@Repository
public interface AuftragAenderungenDao extends JpaRepository<Auftrag, String> {

    /*  PROCEDURE Bemerkung_Aendern (Pi_Pnr          IN     Auftrag.Pnr%TYPE
                             , Pi_Version      IN     Auftrag.Version%TYPE
                             , Pi_Bemerkung    IN     Auf_Bem.Bemerkung%TYPE
                             , Pi_User         IN     Applikation_User.User_Id%TYPE
                             , Po_Vorgang_Id      OUT Vorgaenge.Vorgang_Id%TYPE
                             , Po_Status          OUT NUMBER) IS
                             
                             */

    @Procedure(name = "Auftrag.Bemerkung_Aendern")
    Map<String, Long> editAuftrag(@Param("Pi_Pnr") String pnr,
            @Param("Pi_Version") Long version,
            @Param("Pi_Bemerkung") String bemerkung,
            @Param("Pi_User") String user);

}
