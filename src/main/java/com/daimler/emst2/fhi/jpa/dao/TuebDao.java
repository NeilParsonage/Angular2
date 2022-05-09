package com.daimler.emst2.fhi.jpa.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Tueb;

@Repository
public interface TuebDao extends JpaRepository<Tueb, Long> {
    public List<Tueb> findAllBySystemAndPrognameAndTsprache(String system, String progname, String tsprache);
}
