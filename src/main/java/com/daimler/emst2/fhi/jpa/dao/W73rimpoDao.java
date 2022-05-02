package com.daimler.emst2.fhi.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.W73rimpo;

@Repository
public interface W73rimpoDao extends JpaRepository<W73rimpo, Long> {
}
