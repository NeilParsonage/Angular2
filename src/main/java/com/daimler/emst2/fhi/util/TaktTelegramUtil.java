package com.daimler.emst2.fhi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.dao.LackDao;
import com.daimler.emst2.fhi.jpa.model.Auftraege;
import com.daimler.emst2.fhi.jpa.model.Lack;
import com.daimler.emst2.fhi.model.TaktTelegram;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;

@Component
public class TaktTelegramUtil {

    @Autowired
    LackDao lackDao;

	private static final int DIVISOR_POS_THREE = 1000;
	private static final int DIVISOR_POS_FOUR = DIVISOR_POS_THREE * 10;

	private static final int MAX_LACKSCHLUESSEL_NR = 5;
	private static final int MIN_LACKSCHLUESSEL_NR = 1;

	private static final int SIZE_BAND_NR = 2;
	private static final int SIZE_FBM = 6;
	private static final int SIZE_FGST_END_NR = 6;
	private static final int SIZE_LACKSCHLUESSEL = 4;
	private static final int SIZE_LACKZUSATZ = 2;
	private static final int SIZE_LENKUNG = 1;
	private static final int SIZE_LFD_NR_GES = 4;
	private static final int SIZE_LFD_NR_OTHER = 3;
	private static final int SIZE_PNR = 8;
	private static final int SIZE_STATION = 3;
	private static final int SIZE_TELEGRAMMTYP = 10;
	private static final int SIZE_TIMESTAMP_IN_TELE = 20;
	private static final int SIZE_WKZ = 1;

	private static final String TAKT_MESSAGE_START = "L3T0TAKT  FHI ";

	// SEND
	//      SETFHI
	//      SETRBn
	//      SETMOn
	//      SETUBn
	private static final String TAKT_TELE_TYP_SEND_FHI = "SETFHI";
	private static final String TAKT_TELE_TYP_SEND_RHM = "SETRB";
	private static final String TAKT_TELE_TYP_SEND_LMT = "SETMO";
	private static final String TAKT_TELE_TYP_SEND_UBM = "SETUB";

	// STORNO
	//    Es gibt 4 Ausfallimpulse mit den Telegrammtypen
	//       ASFHI
	//       ASRBn
	//       ASMOn
	//       ASUBn
	private static final String TAKT_TELE_TYP_AUSFALL_FHI = "ASFHI";
	private static final String TAKT_TELE_TYP_AUSFALL_RHM = "ASRB";
	private static final String TAKT_TELE_TYP_AUSFALL_LMT = "ASMO";
	private static final String TAKT_TELE_TYP_AUSFALL_UBM = "ASUB";

	//    und 4 Stornoimpulse mit den Telegrammtypen
	//       AZFHI
	//       AZRBn
	//       AZMOn
	//       AZUBn
	private static final String TAKT_TELE_TYP_STORNO_FHI = "AZFHI";
	private static final String TAKT_TELE_TYP_STORNO_RHM = "AZRB";
	private static final String TAKT_TELE_TYP_STORNO_LMT = "AZMO";
	private static final String TAKT_TELE_TYP_STORNO_UBM = "AZUB";

	// Storno / Ausfalltelegramme
	//    Stelle von  Stelle bis  Länge   Inhalt
	//    1   10  10  L3T0TAKT
	//    11  14  4   FHI
	//    15  24  10  Telegrammtyp
	//    25  32  8   PNR
	//    33  52  20  Datum und Uhrzeit, Beispiel: 28.03.2012.16.09.52
	//    53  56  4   LFD_NR_GES
	//    57  59  3   LFD_NR_BAND
	//    60  62  3   LFD_NR_FHI
	//    63  65  3   LFD_NR_RHM
	//    66  68  3   Leerzeichen, in Wörth mit STATION belegt
	//    69  71  3   LFD_NR_UBM
    public TaktTelegram createCancelTelegramAusfall(SendTypeEnum pSendTypeEnum, Auftraege pAuftrag) {
		//    15  24  10  Telegrammtyp
        long bandNr = pAuftrag.getBandNr();
		String telegramTypString = getTelegramTypAusfall(pSendTypeEnum, bandNr, SIZE_TELEGRAMMTYP);
		TaktTelegram telegram = createCancelTelegramIntern(pSendTypeEnum, pAuftrag, telegramTypString);
		telegram.validate();
		return telegram;
	}

    public TaktTelegram createCancelTelegramStorno(SendTypeEnum pSendTypeEnum, Auftraege pAuftrag) {
		//    15  24  10  Telegrammtyp
        long bandNr = pAuftrag.getBandNr();
		String telegramTypString = getTelegramTypStorno(pSendTypeEnum, bandNr, SIZE_TELEGRAMMTYP);
		TaktTelegram telegram = createCancelTelegramIntern(pSendTypeEnum, pAuftrag, telegramTypString);
		telegram.validate();
		return telegram;
	}

    private TaktTelegram createCancelTelegramIntern(SendTypeEnum pSendTypeEnum, Auftraege pAuftrag,
            String pTelegrammTypString) {
		TaktTelegram taktTelegram = new TaktTelegram();
		//    1   10  10  L3T0TAKT
		//    11  14  4   FHI
		StringBuilder sb = createSendOrCancelSendMessageIntern(pSendTypeEnum, pAuftrag, pTelegrammTypString);

		// ab hier kommt der Storno/Ausfall spezifische Teil
		//    66  68  3   Leerzeichen, in Wörth mit STATION belegt
		sb.append(BasisStringUtils.leftPad(null, SIZE_STATION, BasisStringUtils.SINGLE_BLANK));

		//    69  71  3   LFD_NR_UBM
		Long lfdNrUbm = pAuftrag.getLfdNrUbm();
		String lfdNrUbmString = getLfdNrAsString(SendTypeEnum.UBM, lfdNrUbm, pSendTypeEnum);
		sb.append(lfdNrUbmString);

		taktTelegram.setMessage(sb.toString());

		return taktTelegram;
	}

    private StringBuilder createSendOrCancelSendMessageIntern(SendTypeEnum pSendTypeEnum, Auftraege pAuftrag,
            String pTelegrammTypString) {
		StringBuilder sb = new StringBuilder(TAKT_MESSAGE_START);

		//    15  24  10  Telegrammtyp
		sb.append(pTelegrammTypString);

		//    25  32  8   PNR
		String pnr = BasisStringUtils.getFixedSizeString(pAuftrag.getPnr(), SIZE_PNR);
		sb.append(pnr);

		//    33  52  20  Datum und Uhrzeit, Beispiel: 28.03.2012.16.09.52
		String timestamp = DateTimeHelper.getDateTimeStringForTelegram(DateTimeHelper.getAktuellesDatum());
		timestamp = StringUtils.rightPad(timestamp, SIZE_TIMESTAMP_IN_TELE);
		sb.append(timestamp);

		//    53  56  4   LFD_NR_GES
		Long lfdNrGes = pAuftrag.getLfdNrGes();
		String lfdNrGesString = getLfdNrGesamtAsString(lfdNrGes);
		sb.append(lfdNrGesString);

		//    57  59  3   LFD_NR_BAND
		Long lfdNrLmt = pAuftrag.getLfdNrLmt();
		String lfdNrLmtString = getLfdNrAsString(SendTypeEnum.LMT, lfdNrLmt, pSendTypeEnum);
		sb.append(lfdNrLmtString);

		//    60  62  3   LFD_NR_FHI
		Long lfdNrFhi = pAuftrag.getLfdNrFhi();
		String lfdNrFhiString = getLfdNrAsString(SendTypeEnum.FHI, lfdNrFhi, pSendTypeEnum);
		sb.append(lfdNrFhiString);

		//    63  65  3   LFD_NR_RHM
		Long lfdNrRhm = pAuftrag.getLfdNrRhm();
		String lfdNrRhmString = getLfdNrAsString(SendTypeEnum.RHM, lfdNrRhm, pSendTypeEnum);
		sb.append(lfdNrRhmString);
		return sb;
	}

	//    Stelle von  Stelle bis  Länge   Inhalt
	//    1   10  10  L3T0TAKT
	//    11  14  4   FHI
	//    15  24  10  Telegrammtyp
	//    25  32  8   PNR
	//    33  52  20  Datum und Uhrzeit, Beispiel: 28.03.2012.16.09.52
	//    53  56  4   LFD_NR_GES
	//    57  59  3   LFD_NR_BAND
	//    60  62  3   LFD_NR_FHI
	//    63  65  3   LFD_NR_RHM
	//    66  67  2   BAND_NR
	//    68  73  6   FGSTENDNR (falls vorhanden)
	//    74  74  1   WKZ (falls vorhanden)
	//    75  104 30  LACKDATEN (falls vorhanden)
	//    105 110 6   FBM (falls vorhanden)
	//    111 111 1   LENKUNG (falls vorhanden)
	//    112 112 1   WKZ (falls vorhanden)
	//    113 118 6   FGSTENDNR (falls vorhanden)
	//    119 121 3   LFD_NR_UBM
    public TaktTelegram createSendTelegram(SendTypeEnum pSendTypeEnum, Auftraege pAuftrag) {
		TaktTelegram taktTelegram = new TaktTelegram();
		//    15  24  10  Telegrammtyp
        long bandNr = pAuftrag.getBandNr();
		String telegramTypString = getTelegramTypSendung(pSendTypeEnum, bandNr, SIZE_TELEGRAMMTYP);
		//    1   10  10  L3T0TAKT
		//    11  14  4   FHI
		StringBuilder sb = createSendOrCancelSendMessageIntern(pSendTypeEnum, pAuftrag, telegramTypString);

		// ab hier kommt der Sendungs-spezifische Teil
		//    66  67  2   BAND_NR
		String bandString = BasisStringUtils.leftPad(String.valueOf(bandNr), SIZE_BAND_NR, "0");
		sb.append(bandString);

		//    68  73  6   FGSTENDNR (falls vorhanden)
        String fgstEndnr = pAuftrag.getFgstendnr();
		String fgstEndnrString = BasisStringUtils.leftPad(fgstEndnr, SIZE_FGST_END_NR, BasisStringUtils.SINGLE_BLANK);
		sb.append(fgstEndnrString);

		//    74  74  1   WKZ (falls vorhanden)
		String wkz = pAuftrag.getWkz();
		String wkzString = BasisStringUtils.leftPad(wkz, SIZE_WKZ, BasisStringUtils.SINGLE_BLANK);
		sb.append(wkzString);

		//    75  104 30  LACKDATEN (falls vorhanden)
		//            LPAD (NVL (V_lackschl (1).Lackschl, ' '), 4, ' ')
		//         || LPAD (NVL (V_lackschl (1).Lackzus, ' '), 2, ' ')
		//         || LPAD (NVL (V_lackschl (2).Lackschl, ' '), 4, ' ')
		//         || LPAD (NVL (V_lackschl (2).Lackzus, ' '), 2, ' ')
		//         || LPAD (NVL (V_lackschl (3).Lackschl, ' '), 4, ' ')
		//         || LPAD (NVL (V_lackschl (3).Lackzus, ' '), 2, ' ')
		//         || LPAD (NVL (V_lackschl (4).Lackschl, ' '), 4, ' ')
		//         || LPAD (NVL (V_lackschl (4).Lackzus, ' '), 2, ' ')
		//         || LPAD (NVL (V_lackschl (5).Lackschl, ' '), 4, ' ')
		//         || LPAD (NVL (V_lackschl (5).Lackzus, ' '), 2, ' ');

		String lackdaten = createLackdaten(pAuftrag);
		Assert.isTrue(lackdaten.length() == (MAX_LACKSCHLUESSEL_NR * (SIZE_LACKSCHLUESSEL + SIZE_LACKZUSATZ)));
		sb.append(lackdaten);

		//    105 110 6   FBM (falls vorhanden)
		String fbm = pAuftrag.getFbm();
		String fbmString = BasisStringUtils.leftPad(fbm, SIZE_FBM, BasisStringUtils.SINGLE_BLANK);
		sb.append(fbmString);

		//    111 111 1   LENKUNG (falls vorhanden)
		String lenkung = pAuftrag.getLenkung();
		String lenkungString = BasisStringUtils.leftPad(lenkung, SIZE_LENKUNG, BasisStringUtils.SINGLE_BLANK);
		sb.append(lenkungString);

		//    112 112 1   WKZ (falls vorhanden)
		sb.append(wkzString);

		//    113 118 6   FGSTENDNR (falls vorhanden)
		sb.append(fgstEndnrString);

		//    119 121 3   LFD_NR_UBM
		Long lfdNrUbm = pAuftrag.getLfdNrUbm();
		String lfdNrUbmString = getLfdNrAsString(SendTypeEnum.UBM, lfdNrUbm, pSendTypeEnum);
		sb.append(lfdNrUbmString);

		taktTelegram.setMessage(sb.toString());
		taktTelegram.validate();
		return taktTelegram;
	}

    private String createLackdaten(Auftraege pAuftrag) {
		StringBuilder sb = new StringBuilder();

        List<Lack> auftragLacke = this.lackDao.findByAufPnr(pAuftrag.getPnr());
        // List<Lack> auftragLacke = pAuftrag.getLack();
        Map<Integer, Lack> lackMap = createLackMapForLacke(auftragLacke);
		// Lackdaten erzeugen.
		String lackSchluessel;
		String lackZusatz;
		for (int i = MIN_LACKSCHLUESSEL_NR; i <= MAX_LACKSCHLUESSEL_NR; i++) {
			Integer key = Integer.valueOf(i);
            Lack iLack = lackMap.get(key);
			lackSchluessel = getLackschluesselForTelegram(iLack);
			sb.append(lackSchluessel);

			lackZusatz = getLackzusatzForTelegram(iLack);
			sb.append(lackZusatz);
		}
		return sb.toString();
	}

    private String getLackzusatzForTelegram(Lack iLack) {
		String lackzusatz = null;
		if (iLack != null) {
			lackzusatz = iLack.getLackzus();
		}
		String result = BasisStringUtils.leftPad(lackzusatz, SIZE_LACKZUSATZ, BasisStringUtils.SINGLE_BLANK);
		return result;
	}

    private String getLackschluesselForTelegram(Lack iLack) {
		String lackSchluessel = null;
		if (iLack != null) {
			lackSchluessel = iLack.getLackschl();
		}
		if (lackSchluessel != null && lackSchluessel.length() > SIZE_LACKSCHLUESSEL) {
			// falls 5 Zeichen wird die fuehrende "0" entfernt!
			lackSchluessel = StringUtils.removeStart(lackSchluessel, "0");
			Assert.isTrue(lackSchluessel.length() <= SIZE_LACKSCHLUESSEL);
		}
		lackSchluessel = BasisStringUtils.leftPad(lackSchluessel, SIZE_LACKSCHLUESSEL, BasisStringUtils.SINGLE_BLANK);
		return lackSchluessel;
	}

    private Map<Integer, Lack> createLackMapForLacke(List<Lack> auftragLacke) {
        Map<Integer, Lack> lackMap = new HashMap<Integer, Lack>();
		// Lack in Verarbeitungsmap ablegen
        for (Lack iLack : auftragLacke) {
			Integer lackschlnr = iLack.getLackschlnr();
			lackMap.put(lackschlnr, iLack);
		}
		return lackMap;
	}

    private String getTelegramTypAusfall(SendTypeEnum pEnum, long pBandnr, int pSize) {
		switch (pEnum) {
		case LMT:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_AUSFALL_LMT + pBandnr, pSize);
		case FHI:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_AUSFALL_FHI, pSize); // OHNE Bandnummer
		case RHM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_AUSFALL_RHM + pBandnr, pSize);
		case UBM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_AUSFALL_UBM + pBandnr, pSize);
		case KOMPLETT: // fall through
		case UNDEFINED: // fall through
		default:
			throw new RuntimeException("invalid sendTypeEnum for Ausfalltelegramm: " + pEnum.name());
		}
	}

    private String getTelegramTypStorno(SendTypeEnum pEnum, long pBandnr, int pSize) {
		switch (pEnum) {
		case LMT:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_STORNO_LMT + pBandnr, pSize);
		case FHI:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_STORNO_FHI, pSize); // OHNE Bandnummer
		case RHM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_STORNO_RHM + pBandnr, pSize);
		case UBM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_STORNO_UBM + pBandnr, pSize);
		case KOMPLETT: // fall through
		case UNDEFINED: // fall through
		default:
			throw new RuntimeException("invalid sendTypeEnum for Ausfalltelegramm: " + pEnum.name());
		}
	}

    private String getTelegramTypSendung(SendTypeEnum pEnum, long pBandnr, int pSize) {
		switch (pEnum) {
		case LMT:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_SEND_LMT + pBandnr, pSize);
		case FHI:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_SEND_FHI, pSize); // OHNE Bandnummer
		case RHM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_SEND_RHM + pBandnr, pSize);
		case UBM:
			return BasisStringUtils.getFixedSizeString(TAKT_TELE_TYP_SEND_UBM + pBandnr, pSize);
		case KOMPLETT: // fall through
		case UNDEFINED: // fall through
		default:
			throw new RuntimeException("invalid sendTypeEnum for Ausfalltelegramm: " + pEnum.name());
		}
	}

	private int getDivisorForLfdNrInTelegram(SendTypeEnum pSendTypeEnum) {
		switch (pSendTypeEnum) {
		case UNDEFINED:
			return DIVISOR_POS_FOUR;
		case LMT: // fall through
		case FHI: // fall through
		case RHM: // fall through
		case UBM:
			return DIVISOR_POS_THREE;
		default:
			throw new RuntimeException("Called getNumPositionsForLfdNrInTelegram with invalid sendtype: " + pSendTypeEnum);
		}
	}

	/**
	 * TODO - should use {@link AuftragLfdNrRepresentationUtil}
	 */
	private int getNumPositionsForLfdNrInTelegram(SendTypeEnum pSendTypeEnum) {
		switch (pSendTypeEnum) {
		case UNDEFINED:
			return SIZE_LFD_NR_GES;
		case LMT: // fall through
		case FHI: // fall through
		case RHM: // fall through
		case UBM:
			return SIZE_LFD_NR_OTHER;
		default:
			throw new RuntimeException("Called getNumPositionsForLfdNrInTelegram with invalid sendtype: " + pSendTypeEnum);
		}
	}

	private String getLfdNrAsString(SendTypeEnum pLfdNrSendType, Long pLfdNr, SendTypeEnum pTelegramSendTypeEnum) {
		if (pLfdNr != null) {
			return getTelegramSizeLfdNr(pLfdNrSendType, pLfdNr);
		}
		return BasisStringUtils.leftPad(null, SIZE_LFD_NR_OTHER, BasisStringUtils.SINGLE_BLANK);
	}

	private String getLfdNrGesamtAsString(Long pValue) {
		SendTypeEnum sendType = SendTypeEnum.UNDEFINED;
		return getTelegramSizeLfdNr(sendType, pValue);
	}

	private String getTelegramSizeLfdNr(SendTypeEnum pSendTypeEnum, Long pValue) {
		int sizeLfdNrOther = getNumPositionsForLfdNrInTelegram(pSendTypeEnum);
		long lastPositions = getRelevantValue(pSendTypeEnum, pValue);
		return getImplFixSizeLastPositionsAsString(lastPositions, sizeLfdNrOther);
	}

	private String getImplFixSizeLastPositionsAsString(Long pLastPositions, int pSize) {
		String lastPosString = String.valueOf(pLastPositions);
		String result = BasisStringUtils.leftPad(lastPosString, pSize, "0");
		return result;
	}

	/**
	 * Liefert die auf die Telegrammgroesse reduzierten letzten "n" Stellen der Laufenden Nummer
	 * fuer den uebergebenen Sendungstyp.
	 * Wird als {@link SendTypeEnum} der UNDEFINED Wert uebergeben, so ist die Gesamtlaufende Nummer
	 * gemeint.
	 * @param pSendTypeEnum
	 * @param pValue
	 * @return
	 */
	public long getRelevantValue(SendTypeEnum pSendTypeEnum, Long pValue) {
		int divisor = getDivisorForLfdNrInTelegram(pSendTypeEnum);
		long lastPositions = (pValue % divisor);
		return lastPositions;
	}


}
