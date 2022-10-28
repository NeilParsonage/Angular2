package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Vorgaenge;

@Repository
public interface VorgaengeDao extends JpaRepository<Vorgaenge, Long> {

}
