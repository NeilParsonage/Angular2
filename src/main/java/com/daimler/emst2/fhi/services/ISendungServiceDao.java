package com.daimler.emst2.fhi.services;

import com.daimler.emst2.fhi.jpa.dao.AuftragDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragSperrenDao;
import com.daimler.emst2.fhi.jpa.dao.SystemwertDao;

public interface ISendungServiceDao {

    public SystemwertDao getSystemwertDao();

    public AuftragDao getAuftragDao();

    public AuftragSperrenDao getAuftragSperrenDao();

}
