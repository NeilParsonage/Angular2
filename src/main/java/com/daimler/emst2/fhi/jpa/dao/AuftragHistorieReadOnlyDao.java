package com.daimler.emst2.fhi.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragHistorieReadOnly;


@Repository
public interface AuftragHistorieReadOnlyDao
        extends CrudRepository<AuftragHistorieReadOnly, String>, JpaSpecificationExecutor<AuftragHistorieReadOnly> {


    @Query("SELECT a from AuftragHistorieReadOnly a where a.aufHistId > :von and a.aufHistId < :bis")
    public List<AuftragHistorieReadOnly> findAllAuftragHistorieReadOnly(@Param("von") long von, @Param("bis") long bis);

}
