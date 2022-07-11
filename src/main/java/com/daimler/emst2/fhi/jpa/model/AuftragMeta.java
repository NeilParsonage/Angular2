package com.daimler.emst2.fhi.jpa.model;

import java.util.List;

import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;

public class AuftragMeta {

    private final Auftrag auftrag;

    private boolean isSendbar;

    private List<AuftragSperrInformation> ankuendigungInfos;
    private List<AuftragSperrInformation> sperrInfos;
    private List<AuftragSperrInformation> sperrInfosFHI;
    private List<AuftragSperrInformation> sperrInfosLMT;
    private List<AuftragSperrInformation> sperrInfosSonst;

    private AuftragMeta(Auftrag auftrag) {
        this.auftrag = auftrag;
    }

    public static AuftragMeta create(Auftrag auftrag) {
        return new AuftragMeta(auftrag);
    }

    public List<AuftragSperrInformation> getAnkuendigungInfos() {
        return ankuendigungInfos;
    }

    public void setAnkuendigungInfos(List<AuftragSperrInformation> ankuendigungInfos) {
        this.ankuendigungInfos = ankuendigungInfos;
    }

    public List<AuftragSperrInformation> getSperrInfos() {
        return sperrInfos;
    }

    public void setSperrInfos(List<AuftragSperrInformation> sperrInfos) {
        this.sperrInfos = sperrInfos;
    }

    public SendStatusEnum getFhiSendStatusEnum() {
        return SendStatusEnum.getSendStatus(auftrag.getFhiSendStatus());
    }

    public SendStatusEnum getLmtSendStatusEnum() {
        return SendStatusEnum.getSendStatus(auftrag.getLmtSendStatus());
    }

    public Integer getTransientSollabstandFhi() {
        if (getFhiSendStatusEnum().isGesendet()) {
            return null;
        }
        return auftrag.getSendeVorschlagDetails().getSollabsFhi();
    }

    public Integer getTransientSollabstandLmt() {
        if (getLmtSendStatusEnum().isGesendet()) {
            return null;
        }
        return auftrag.getSendeVorschlagDetails().getSollabsLmt();
    }

    public List<AuftragSperrInformation> getSperrInfosFHI() {
        return sperrInfosFHI;
    }

    public void setSperrInfosFHI(List<AuftragSperrInformation> sperrInfosFHI) {
        this.sperrInfosFHI = sperrInfosFHI;
    }

    public List<AuftragSperrInformation> getSperrInfosLMT() {
        return sperrInfosLMT;
    }

    public void setSperrInfosLMT(List<AuftragSperrInformation> sperrInfosLMT) {
        this.sperrInfosLMT = sperrInfosLMT;
    }

    public List<AuftragSperrInformation> getSperrInfosSonst() {
        return sperrInfosSonst;
    }

    public void setSperrInfosSonst(List<AuftragSperrInformation> sperrInfosSonst) {
        this.sperrInfosSonst = sperrInfosSonst;
    }

    public boolean isSendbar() {
        return isSendbar;
    }

    public void setSendbar(boolean isSendbar) {
        this.isSendbar = isSendbar;
    }

}
