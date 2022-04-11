package com.daimler.emst2.fhi.util;

import org.apache.commons.lang3.StringUtils;

import com.daimler.emst2.fhi.jpa.dao.AktiveCodesHistDao;
import com.daimler.emst2.fhi.jpa.model.AktiveCodesHist;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.model.BandAuswahl;

public class HistCodeDaoHelper {

    public static final HistCodeDaoHelper create() {
        return new HistCodeDaoHelper();
    }

    public int deleteOldCodesForBereich(AktiveCodesHistDao histCodeDao, Auftraege pAuftrag,
            BandAuswahl pBandauswahl) {
        String pnr = pAuftrag.getPnr();
        String bereichLikePattern = null;
        if (pBandauswahl.isBereichFhi()) {
            bereichLikePattern = pBandauswahl.getBereich();
        }
        else if (pBandauswahl.isLmtBandauswahl()) {
            bereichLikePattern = StringUtils.substring(pBandauswahl.getBereich(), 0, 1);
            bereichLikePattern += "%";
        }
        else {
            throw new RuntimeException("Unsupported Bandauswahl for delete history: " + pBandauswahl);
        }
//        SQLQuery query = getSession().createSQLQuery(SQL_DELETE_OLD_HIST);
//        query.setString(0, pnr);
//        query.setString(1, bereichLikePattern);
//        int numDeletes = query.executeUpdate();

        int numDeletes = histCodeDao.deleteOldCodesForBereich(pnr, bereichLikePattern);

        return numDeletes;
    }

    public static void saveCodesInHist(AktiveCodesHistDao histCodeDao, String pnr, BandAuswahl auftragBandAuswahl,
            String codesBand) {
        AktiveCodesHist toSave = new AktiveCodesHist();

        toSave.setAufPnr(pnr);
        toSave.setTyp(auftragBandAuswahl.getBereich());
        toSave.setCodes(codesBand);

        histCodeDao.save(toSave);
    }

}
