package com.daimler.emst2.fhi.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Tueb;

@Repository
public interface TuebDao extends CrudRepository<Tueb, Long> {

    @Query("SELECT t from Tueb t WHERE t.system = :system and t.tsprache = :sprache")
    public List<Tueb> findTuebsBySystemAndSprache(@Param("system") String pnr,
            @Param("sprache") String sprache);
}
