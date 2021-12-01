package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragTermine;

@Repository
public interface AuftragTermineDao extends JpaRepository<AuftragTermine, String> {

    @Query("SELECT a from AuftragTermine a WHERE a.pnr = :pnr")
    public AuftragTermine findAuftragTermineByPnr(@Param("pnr") String pnr);

}
