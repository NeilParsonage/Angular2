package com.daimler.emst2.fhi.jpa.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.AuftragSendestatus;

@Repository
public interface AuftragSendestatusDao extends CrudRepository<AuftragSendestatus, String> {

}
