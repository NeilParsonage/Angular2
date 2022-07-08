package com.daimler.emst2.fhi.sendung.processcommon.precondition;

import java.util.Date;

import com.daimler.emst2.fhi.jpa.dao.AktiveRestriktionDao;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.constants.ProtocolMessageEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class PreconditionRestriktionenUpToDate extends AbstractSendPrecondition {

    private AktiveRestriktionDao aktiveRestriktionDao;

    public PreconditionRestriktionenUpToDate(SendPreconditionEnum pPreconditionEnum,
            ProtocolService pProtocolService, AktiveRestriktionDao pAktiveRestriktionDao) {
        super(pPreconditionEnum, pProtocolService);
        aktiveRestriktionDao = pAktiveRestriktionDao;
    }

    @Override
    public boolean doPrepareContext(SendContext pSendContext) {
        // Date letzteBekannteAenderung = pSendContext.getLetzteAenderungRestriktionen();
        Date letzteBekannteAenderung = pSendContext.aktiveRestriktionenMetaList.updatedOn;

		if (letzteBekannteAenderung == null) {
			return true; // es liegen keine Daten vor...
		}

        // 1. hole den juengsten Aktualisierungswert fuer "aktiveRestriktionen" aus der DB
        boolean existsUnknownChange = aktiveRestriktionDao.updDateGreaterThan(letzteBekannteAenderung) > 0;
        boolean isOk = !existsUnknownChange;

        // 2. Ist der Wert in der DB neuer, so ist ein ERROR zu erzeugen und false zu liefern
        if (!isOk) {
            getProtocolService().addProtocolEntry(pSendContext, ProtocolMessageEnum.RESTRIKTIONEN_OUTDATED,
                    getIdentifier(), SeverityEnum.FATAL);
        }
        getIdentifier();
        return isOk;
    }

    public void setAktiveRestriktionDao(AktiveRestriktionDao pAktiveRestriktionDao) {
        aktiveRestriktionDao = pAktiveRestriktionDao;
    }
}
