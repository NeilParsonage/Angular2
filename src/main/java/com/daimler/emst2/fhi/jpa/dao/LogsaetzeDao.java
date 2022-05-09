package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Logsaetze;

@Repository
public interface LogsaetzeDao extends CrudRepository<Logsaetze, Long> {

}
