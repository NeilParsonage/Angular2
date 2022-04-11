package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Systemgruppen;

@Repository
public interface SystemgruppenDao extends JpaRepository<Systemgruppen, Long> {
    public Systemgruppen findByGruppeName(String name);
}
