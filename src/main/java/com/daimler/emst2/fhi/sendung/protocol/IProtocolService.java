package com.daimler.emst2.fhi.sendung.protocol;

import com.daimler.emst2.fhi.model.IDbResultWithProtocol;
import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.IProtocol;
import com.daimler.emst2.fhi.model.IProtocolEntry;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;

public interface IProtocolService {

    /**
     * Create new Protocol to collect protocol entries.
     * 
     * @param pActionId - process to protocol
     * @param pLogAusloeserDetails - info string for logging in logsaetze
     * @return initialized protocol
     */
    IProtocol create(IProcessId pActionId, String pLogAusloeserDetails);

    /**
     * Legt einen ProtocolEntry zu den uebergebenen Parametern an und fuegt diesen dem uebergebenen Protocol hinzu.
     */
    void addProtocolEntry(IProtocol pProtocol,
            ProtocolMessageEnum pMessageEnum, IProcessId pTaskId,
            SeverityEnum pSeverityEnum);

    /**
     * Legt einen ProtocolEntry zu den uebergebenen Parametern an und fuegt diesen dem uebergebenen Protocol hinzu.
     */
    void addProtocolEntry(IProtocol pProtocol,
            ProtocolMessageEnum pMessageEnum, String pMessageParam,
            IProcessId pTaskId, SeverityEnum pSeverityEnum);

    /**
     * Legt einen ProtocolEntry zu den uebergebenen Parametern an und fuegt diesen dem uebergebenen Protocol hinzu.
     */
    void addProtocolEntry(IProtocol pProtocol,
            ProtocolMessageEnum pMessageEnum, String[] pMessageParamArray,
            IProcessId pTaskId, SeverityEnum pSeverityEnum);

    /**
     * Legt einen ProtocolEntry mit den uebergebenen Parametern an und liefert diesen zurueck.
     */
    IProtocolEntry createProtocolEntry(
            ProtocolMessageEnum pMessageEnum, String pMessageParam,
            IProcessId pTaskId, SeverityEnum pSeverityEnum);

    /**
     * Legt einen ProtocolEntry mit den uebergebenen Parametern an und liefert diesen zurueck.
     */
    IProtocolEntry createProtocolEntry(
            ProtocolMessageEnum pMessageEnum, String[] pMessageParamArray,
            IProcessId pTaskId, SeverityEnum pSeverityEnum);

    /**
     * @param protocol
     * @param actionEnum
     */
    void addDebugProtocolEntry(IProtocol protocol,
            IProcessId processId);

    /**
     * Create new Protocol and store it in given dbResult-container.
     * Protocol is created with a ProtocolEntry. Its message is taken from the Meldung-Vorgang
     * of the given dbResult.
     * @param dbResult
     * @param processId - Id for Protocol and the ProtocolEntry.
     * @param pUser - current user
     */
    void completeDbResultProtocol(IDbResultWithProtocol dbResult,
            IProcessId processId, IProcessId messageVorgangId);

    void addInfoProtocolEntry(IProtocol pProtocol,
            IProcessId processId, String tuebKey, String param);

}