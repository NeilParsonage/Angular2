package com.daimler.emst2.fhi.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Wertelisten;

@Repository
public interface WertelistenDao extends JpaRepository<Wertelisten, Long> {

    @Query("SELECT w from Wertelisten w WHERE w.liste = :liste")
    public List<Wertelisten> findAllByListe(@Param("liste") String liste);

}
