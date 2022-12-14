package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragKabelsaetze;

@Repository
public interface AuftragKabelsaetzeDao extends CrudRepository<AuftragKabelsaetze, String> {

    @Query("SELECT a from AuftragKabelsaetze a WHERE a.pnr = :pnr order by a.kabelsatz")
    public List<AuftragKabelsaetze> findKabelsaetzeByPnr(@Param("pnr") String pnr);

}
