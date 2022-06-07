package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.AuftragKriterien;

@Repository
public interface AuftragKriterienDao extends CrudRepository<Auftraege, String> {

    @Query("SELECT a from AuftragKriterien a WHERE a.pnr = :pnr order by relevant, kriterium")
    public List<AuftragKriterien> findKriterienByPnr(@Param("pnr") String pnr);

}
