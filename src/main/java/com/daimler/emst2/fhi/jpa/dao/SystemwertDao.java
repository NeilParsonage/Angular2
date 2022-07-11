package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Systemwert;

@Repository
public interface SystemwertDao extends JpaRepository<Systemwert, Long> {
    public Systemwert findByWertName(String name);
}