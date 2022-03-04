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

}
