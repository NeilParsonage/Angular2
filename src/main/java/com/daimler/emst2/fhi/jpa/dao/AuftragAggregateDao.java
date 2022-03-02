package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragAggregate;

@Repository
public interface AuftragAggregateDao extends CrudRepository<AuftragAggregate, String> {

    @Query("SELECT a from AuftragAggregate a WHERE a.pnr = :pnr order by a.aggregat")
    public List<AuftragAggregate> findAuftragAggregateByPnr(@Param("pnr") String pnr);

}
