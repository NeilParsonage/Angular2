package com.daimler.emst2.fhi.services;

import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public interface ISendungService {

    public KonfigurationService getConfigService();

    public RestriktionenService getRestriktionenService();

    public ProtocolService getProtocolService();

    public KriterienService getKriterienService();

    public AuftragService getAuftragService();

}
