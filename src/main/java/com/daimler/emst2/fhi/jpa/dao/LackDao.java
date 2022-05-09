package com.daimler.emst2.fhi.jpa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daimler.emst2.fhi.jpa.model.Lack;

@Repository
public interface LackDao extends CrudRepository<Lack, String> {
    public List<Lack> findByAufPnr(String aufPnr);
}