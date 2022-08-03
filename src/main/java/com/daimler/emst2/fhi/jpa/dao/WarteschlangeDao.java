package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Warteschlange;

@Repository
public interface WarteschlangeDao extends CrudRepository<Warteschlange, String> {

    List<Warteschlange> findByPnr(String name);

    @Query("SELECT w from Warteschlange w WHERE w.pnr = :pnr")
    public Warteschlange findEntryByPnr(@Param("pnr") String pnr);


}
