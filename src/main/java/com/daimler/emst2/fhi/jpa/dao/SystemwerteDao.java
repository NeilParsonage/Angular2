package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Systemwerte;

@Repository
public interface SystemwerteDao extends JpaRepository<Systemwerte, Long> {
    public Systemwerte findByWertName(String name);
}