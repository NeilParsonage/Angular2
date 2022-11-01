package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragHistorieReadOnly;


@Repository
public interface AuftragHistorieReadOnlyDao extends CrudRepository<AuftragHistorieReadOnly, String> {


    @Query("SELECT a from AuftragHistorieReadOnly a")
    public List<AuftragHistorieReadOnly> findAllAuftragHistorieReadOnlyD();

}
