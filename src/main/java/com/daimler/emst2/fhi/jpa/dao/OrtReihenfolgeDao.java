package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.OrtReihenfolge;

@Repository
public interface OrtReihenfolgeDao extends CrudRepository<OrtReihenfolge, String> {


    @Query("SELECT o from OrtReihenfolge  o where o.ort like :ort")
    public OrtReihenfolge findOrtReihenfolgeForOrt(@Param("ort") String ort);


}
