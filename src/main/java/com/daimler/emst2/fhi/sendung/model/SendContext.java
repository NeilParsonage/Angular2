package com.daimler.emst2.fhi.sendung.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.daimler.emst2.fhi.dto.ProtocolEntryDTO;
import com.daimler.emst2.fhi.jpa.dao.AuftragSperrenDao;
import com.daimler.emst2.fhi.jpa.dao.SystemwertDao;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragSperren;
import com.daimler.emst2.fhi.jpa.model.IAuftragSperrenForBereich;
import com.daimler.emst2.fhi.jpa.model.Systemwert;
import com.daimler.emst2.fhi.model.FhiMandantEnum;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessContext;
import com.daimler.emst2.fhi.sendung.processcommon.IAuftragLfdNrProcessContext;
import com.daimler.emst2.fhi.sendung.processcommon.IOrtsdatenProcessContext;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.util.DateTimeHelper;

public class SendContext implements IProcessContext, IAuftragLfdNrProcessContext, IOrtsdatenProcessContext {

    public Map<SendCheckEnum, ProtocolEntryDTO> userProtocolSendChecks;
    public String mandant;
    public FhiMandantEnum mandantEnum;
    public MetaList<AktiveRestriktion> aktiveRestriktionenMetaList;
    public Auftrag auftrag;
    public List<AktiveRestriktion> auftragAktiveRestriktionList;
    public List<String> errorMessages = new ArrayList<String>();
    public SendTypeEnum sendTypeEnum;
    public String user;
    public Date processTimestamp;

    public List<AuftragSperren> auftragSperrenList;
    public List<IAuftragSperrenForBereich> anzahlSperrenForBereich;
    public AuftragSperrenDao auftragSperrenDao;
    public SystemwertDao systemwertDao;

    private final Set<SendTypeEnum> performedSendSet = new HashSet<SendTypeEnum>();

    private Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> ortChecksMap =
            new HashMap<OrtCheckEnum, Map<OrtTypEnum, Set<String>>>();

    public Protocol protocol = null;

    private Map<String, Systemwert> lfdNrMap;

    private SendContext() {
        this.processTimestamp = DateTimeHelper.getAktuellesDatum();
    }

    public final static SendContext create() {
        return new SendContext();
    }

    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }

    public SendTypeEnum getSendTypeEnum() {
        return sendTypeEnum;
    }

    public void setSendTypeEnum(SendTypeEnum sendTypeEnum) {
        this.sendTypeEnum = sendTypeEnum;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public Date getProcessTimestamp() {
        return processTimestamp;
    }

    public String getMandant() {
        return mandant;
    }

    public FhiMandantEnum getMandantEnum() {
        return mandantEnum;
    }

    public MetaList<AktiveRestriktion> getAktiveRestriktionenMetaList() {
        return aktiveRestriktionenMetaList;
    }

    @Override
    public Auftrag getAuftrag() {
        return auftrag;
    }

    public List<AktiveRestriktion> getAuftragAktiveRestriktionList() {
        return auftragAktiveRestriktionList;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addPerformedSendToCollection(SendTypeEnum pEnum) {
        getPerformedSendCollection().add(pEnum);
    }

    public Collection<SendTypeEnum> getPerformedSendCollection() {
        return performedSendSet;
    }

    @Override
    public void setCurrentLfdNrMap(Map<String, Systemwert> pLfdNrMap) {
        this.lfdNrMap = pLfdNrMap;
    }

    @Override
    public Map<String, Systemwert> getCurrentLfdNrMap() {
        return lfdNrMap;
    }

    @Override
    public Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> getOrtChecksMap() {
        return ortChecksMap;
    }

    @Override
    public void setOrtChecksMap(Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> pOrtChecksMap) {
        this.ortChecksMap = pOrtChecksMap;
    }

}
