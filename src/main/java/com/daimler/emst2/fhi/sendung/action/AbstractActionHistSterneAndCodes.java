package com.daimler.emst2.fhi.sendung.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.daimler.emst2.fhi.jpa.dao.AktiveCodesHistDao;
import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionHistDao;
import com.daimler.emst2.fhi.jpa.model.AktiveRestriktion;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.BandAuswahl;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.util.AktiveRestriktionHistDaoHelper;
import com.daimler.emst2.fhi.util.AuftragUtil;
import com.daimler.emst2.fhi.util.HistCodeDaoHelper;

public abstract class AbstractActionHistSterneAndCodes extends AbstractSendAction {

    private static final Log LOG = LogFactory.getLog(AbstractActionHistSterneAndCodes.class);

    AktiveRestriktionHistDao aktiveRestriktionHistDao;

    AktiveCodesHistDao histCodeDao;

    public AbstractActionHistSterneAndCodes(SendActionEnum pSendActionEnum, ProtocolService pProtocolService) {
        super(pSendActionEnum.getTyp(), pSendActionEnum, pProtocolService);
    }

    @Override
    protected final void init() {
        // no static preconditions
        // sendAction is specified via constructor
    }

    @Override
    public final boolean doExecuteImpl(SendContext pContext) {
        Auftraege auftrag = pContext.getAuftrag();

        // 1. remove old entries from codes and restriktionen for sendung/bereich
        BandAuswahl auftragBandAuswahl = getHistBandAuswahl(auftrag);
        boolean validAuftragBandauswahl = isValidBandauswahl(auftragBandAuswahl);
        if (validAuftragBandauswahl) {
            int numDeletedRestrictions = AktiveRestriktionHistDaoHelper.create()
                    .deleteOldRestriktionenForBereich(aktiveRestriktionHistDao, auftrag, auftragBandAuswahl);
            if (LOG.isDebugEnabled()) {
                LOG.debug("num '" + numDeletedRestrictions + "' hist restrictions deleted' for bereich '" + auftragBandAuswahl.getBereich() + "'");
            }
        }

        // 2. copy transAktiveRestriktion Liste to histRestriktionen Liste
        //List<AktiveRestriktion> transAktiveRestriktionList = auftrag.getTransAktiveRestriktionList();
        List<AktiveRestriktion> transAktiveRestriktionList = pContext.aktiveRestriktionenMetaList.daten;

        for (AktiveRestriktion aktiveRestriktion : transAktiveRestriktionList) {
            String bereich = aktiveRestriktion.getBereich();
            BandAuswahl restriktionBandAuswahl = BandAuswahl.getBandAuswahl(bereich);
            if (validAuftragBandauswahl && restriktionBandAuswahl.equals(auftragBandAuswahl)) {
                // aktiveRestriktionHistDao.saveAktiveRestriktionInHist(aktiveRestriktion, auftrag.getPnr());
                AktiveRestriktionHistDaoHelper.create().saveAktiveRestriktionInHist(aktiveRestriktionHistDao,
                        aktiveRestriktion,
                        auftrag.getPnr());
            }
            else {
                LOG.debug("aktive Restriktion mit Bereich '" + aktiveRestriktion.getBereich() + "' nicht in HistRestriktionen aufgenommen");
            }
        }
        if (validAuftragBandauswahl) {
            //int numDeletedCodes = histCodeDao.deleteOldCodesForBereich(auftrag, auftragBandAuswahl);

            int numDeletedCodes =
                    HistCodeDaoHelper.create().deleteOldCodesForBereich(histCodeDao, auftrag, auftragBandAuswahl);

            if (LOG.isDebugEnabled()) {
                LOG.debug("num '" + numDeletedCodes + "' hist codes deleted' for bereich '" + auftragBandAuswahl.getBereich() + "'");
            }

            // auftrag.getCodesBand()
            // LEFT OUTER JOIN Mv_Alle_Rel_Codes Rel ON (Rel.Pnr = A.Pnr)
            HistCodeDaoHelper.saveCodesInHist(this.histCodeDao, auftrag.getPnr(), auftragBandAuswahl,
                    AuftragUtil.getCodesBand(auftrag));
        }

        getProtocolService().addDebugProtocolEntry(pContext, getIdentifier()); // CHECK
        return true;
    }

    private boolean isValidBandauswahl(BandAuswahl histBandAuswahl) {
        return (histBandAuswahl.isBereichFhi() || histBandAuswahl.isLmtBandauswahl());
    }

    protected abstract BandAuswahl getHistBandAuswahl(Auftraege pAuftrag);

    public void setHistRestriktionDao(AktiveRestriktionHistDao histRestriktionDao) {
        this.aktiveRestriktionHistDao = histRestriktionDao;
    }

    public void setHistCodeDao(AktiveCodesHistDao histCodeDao) {
        this.histCodeDao = histCodeDao;
    }

}
