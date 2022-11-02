package com.daimler.emst2.fhi.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.dto.AuftragHistorieDTO;
import com.daimler.emst2.fhi.dto.FhiDtoFactory;
import com.daimler.emst2.fhi.jpa.dao.AuftragHistorieCustomDao;
import com.daimler.emst2.fhi.jpa.dao.AuftragHistorieReadOnlyDao;
import com.daimler.emst2.fhi.jpa.model.Auftrag;
import com.daimler.emst2.fhi.jpa.model.AuftragHistorie;
import com.daimler.emst2.fhi.jpa.model.AuftragHistorieReadOnly;
import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;
import com.daimler.emst2.fhi.sendung.constants.CancelSendTypeEnum;
import com.daimler.emst2.fhi.sendung.constants.SendStatusEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.constants.SperrtypEnum;
import com.daimler.emst2.fhi.sendung.process.comparator.ProcessIdComparator;
import com.daimler.emst2.fhi.sendung.processcommon.CalcSendeterminForHistUtil;
import com.daimler.emst2.fhi.util.AuftragUtil;
import com.daimler.emst2.fhi.util.BasisStringUtils;


@Service
public class AuftragHistorienService {

    public static final String MELDER_PNR_DELETED = "ZER";

    private static final String MELDEKENN_PNR_DELETED = "DIA";

    private static final String MELDUNG_PNR_DELETED = "Auftrag mit PNR {0} zerstört";

    private static final String MELDER_SPERR = "SPER";

    private static final String MELDER_ANK = "ANK";

    private static final String MELDEKENN_SA_AUFHEBEN = "FRE";

    private static final String MELDEKENN_SA_SETZEN = "SPR";

    private static final String MELDUNG_SA_FREIGEBEN_PATTERN = "{0} aufgehoben: {1}";

    private static final String MELDUNG_SA_SETZEN_PATTERN = "{0} gesetzt: {1}";

    @Autowired
    private AuftragHistorieCustomDao auftragHistorieCustomDao;

    @Autowired
    AuftragHistorieReadOnlyDao auftragHistorieReadOnlyDao;

    @Autowired
    FhiDtoFactory dtoFactory;

    public AuftragHistorienService() {
        super();
    }

    public List<AuftragHistorieDTO> getAlleAuftragHistorie() {

        //List<AuftragHistorieReadOnly> result = this.auftragHistorieReadOnlyDao.findAllAuftragHistorieReadOnly();
        List<AuftragHistorieReadOnly> result =
                this.auftragHistorieReadOnlyDao.findAllAuftragHistorieReadOnly(69646040, 69646140);

        return result instanceof List
                ? result.stream().map(x -> dtoFactory.createAuftragAenderungstexteDTO(x))
                        .collect(Collectors.toList())
                : Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    public void createSperreAnkuendigungAufhebenHistorie(Auftrag pAuftrag,
            AuftragSperrInformation pSperrInformation) {
        String pnr = pSperrInformation.getPnr();
        SperrtypEnum sperrtypEnum = pSperrInformation.getSperrtypEnum();
        String sperrcode = pSperrInformation.getSperrcode();
        String meldungenText =
            MessageFormat.format(MELDUNG_SA_FREIGEBEN_PATTERN, new Object[] { sperrtypEnum.getCode2char2(),
                    sperrcode });
        meldungenText = StringUtils.substring(meldungenText, 0, AuftragHistorie.MAX_LEN_MELDUNG);
        Date sendeterminForHist = CalcSendeterminForHistUtil.calculateSimpleSendetermin(pAuftrag);
        String melder = sperrtypEnum == SperrtypEnum.ANKUENDIGUNG ? MELDER_ANK : MELDER_SPERR;
        this.auftragHistorieCustomDao.saveAuftragHistorie(pnr, melder, MELDEKENN_SA_AUFHEBEN, meldungenText,
                sendeterminForHist);
    }

    /**
     * {@inheritDoc}
     */
    public void createSperreAnkuendigungSetzenHistorie(Auftrag pAuftrag, AuftragSperrInformation pSperrInformation) {
        String pnr = pSperrInformation.getPnr();
        SperrtypEnum sperrtypEnum = pSperrInformation.getSperrtypEnum();
        String sperrcode = pSperrInformation.getSperrcode();
        String meldungenText =
            MessageFormat.format(MELDUNG_SA_SETZEN_PATTERN,
                    new Object[] { sperrtypEnum.getCode2char2(), sperrcode });
        meldungenText = StringUtils.substring(meldungenText, 0, AuftragHistorie.MAX_LEN_MELDUNG);
        Date sendeterminForHist = CalcSendeterminForHistUtil.calculateSimpleSendetermin(pAuftrag);
        String melder = sperrtypEnum == SperrtypEnum.ANKUENDIGUNG ? MELDER_ANK : MELDER_SPERR;
        getAuftragHistorieCustomDao().saveAuftragHistorie(pnr, melder, MELDEKENN_SA_SETZEN, meldungenText,
                sendeterminForHist);
    }

    /**
     * {@inheritDoc}
     */
    public void createStornoHistorie(SendTypeEnum pUserAction, Auftrag pAuftrag,
            Map<SendTypeEnum, CancelSendTypeEnum> pPerformedStornoTypes, Date pProcessStartTimestamp) {
        String pnr = pAuftrag.getPnr();
        StornoMessageFactory smf =
            new StornoMessageFactory(pUserAction, pAuftrag, pPerformedStornoTypes, pProcessStartTimestamp);
        String meldung = smf.getMessage();
        String meldeKennung = smf.getMeldekenn();
        String melder = smf.getMelder();
        Date stornoterminForHist = smf.getStornoterminForHist();
        getAuftragHistorieCustomDao().saveAuftragHistorie(pnr, melder, meldeKennung, meldung, stornoterminForHist);
    }

    /**
     * {@inheritDoc}
     */
    public void createSendungHistorie(SendTypeEnum pUserAction, Auftrag pAuftrag,
            Collection<SendTypeEnum> pPerformedSendTypes, Date pProcessStartTimestamp) {
        String pnr = pAuftrag.getPnr();
        List<SendTypeEnum> performedSendTypesCopy = new ArrayList<SendTypeEnum>(pPerformedSendTypes);
        SendMessageFactory smf =
            new SendMessageFactory(pUserAction, pAuftrag, performedSendTypesCopy, pProcessStartTimestamp);
        String meldung = smf.getMessage();
        String meldeKennung = smf.getMeldekenn();
        String melder = smf.getMelder();
        Date sendeterminForHist = smf.getSendeterminForHist();
        getAuftragHistorieCustomDao().saveAuftragHistorie(pnr, melder, meldeKennung, meldung, sendeterminForHist);
    }

    public void createAuftragZerstoertHistorie(Auftrag auftrag) {
        String meldung = MessageFormat.format(MELDUNG_PNR_DELETED, auftrag.getPnr());
        Date sendeterminForHist = CalcSendeterminForHistUtil.calculateSimpleSendetermin(auftrag);
        getAuftragHistorieCustomDao().saveAuftragHistorie(auftrag, MELDER_PNR_DELETED, MELDEKENN_PNR_DELETED,
                meldung,
                sendeterminForHist);
    }

    private AuftragHistorieCustomDao getAuftragHistorieCustomDao() {
        return auftragHistorieCustomDao;
    }

    public void setAuftragHistorieCustomDao(AuftragHistorieCustomDao pAuftragHistorieDao) {
        auftragHistorieCustomDao = pAuftragHistorieDao;
    }

    /**
     * Private inner class that prepares the data for a history entry.
     * @author THB
    //        Es wird f r die angestossene Benutzeraktion (z.B.  Komplettsendung ) genau ein Historieneintrag nach folgendem Inhalt erstellt.
    //        <Aktionstyp> <Aktion> [(VS)]? (<Teilsendungstyp-A>, )
    //        Beispiele daf r:
    //        Komplettsendung (mit Vorsendung)   durchgef hrte Teilsendungen LMT, RHM, FHI
    //         Sendung kompl. (VS) LMT, RHM, UBM 
    //        Teilsendung LMT (mit Vorsendung)   durchgef hrte Teilsendungen LMT, RHM, UBM
    //         Sendung teil. LMT (VS) RHM, UBM 
     */
    private class SendMessageFactory {
        private static final String MELDER = "SEND";

        // fuer Teilsendungen entspricht die Meldekenn dem SendTypeEnum
        private static final String MELDKENN_KOMPLETT = "KSO";

        private static final String MELDKENN_KOMPLETT_MIT_VORSENDUNG = "KSV";

        private static final String BRACKET_OPEN = "(";

        private static final String BRACKET_CLOSE = ")";

        private static final String TOKEN_VORSENDUNG_START = "-" + BRACKET_OPEN + "TS:";

        private static final String TOKEN_AKTION_SENDUNG_KOMPLETT = "Sendung kompl.";

        private static final String TOKEN_AKTION_SENDUNG_TEIL = "Sendung teil.";

        private static final String SEP = ",";

        private final SendTypeEnum userAction;

        private final Auftrag auftrag;

        private final List<SendTypeEnum> performedSendTypes;

        private final Date processStartTimestamp;

        private List<SendTypeEnum> vorsendungenList = null;

        protected SendMessageFactory(SendTypeEnum pUserAction, Auftrag pAuftrag,
                List<SendTypeEnum> pPerformedSendTypes, Date pProcessStartTimestamp) {
            super();
            Assert.isTrue(!SendTypeEnum.UNDEFINED.equals(pUserAction));
            Assert.isTrue(pUserAction != null);
            userAction = pUserAction;
            auftrag = pAuftrag;
            performedSendTypes = pPerformedSendTypes;
            Collections.sort(performedSendTypes, new ProcessIdComparator<SendTypeEnum>());
            processStartTimestamp = pProcessStartTimestamp;
        }

        public String getMelder() {
            return MELDER;
        }

        public String getMeldekenn() {
            if (!userAction.isKomplett()) {
                return userAction.name();
            }
            else {
                if (hasAuftragVorsendung()) {
                    return MELDKENN_KOMPLETT_MIT_VORSENDUNG;
                }
                else {
                    return MELDKENN_KOMPLETT;
                }
            }
        }

        public String getMessage() {
            StringBuilder result = new StringBuilder();
            if (userAction.isKomplett()) {
                result.append(TOKEN_AKTION_SENDUNG_KOMPLETT);
            }
            else {
                result.append(TOKEN_AKTION_SENDUNG_TEIL);
                result.append(BasisStringUtils.SINGLE_BLANK);
                result.append(userAction.name());
            }
            result.append(BasisStringUtils.SINGLE_BLANK);

            // transform SendTypeEnum of the performed (Teil)-Sendungen to String
            @SuppressWarnings("unchecked")
            List<String> teilsendToken = ListUtils.transformedList(performedSendTypes, new Transformer() {
                @Override
                public Object transform(Object pInput) {
                    SendTypeEnum sendTypeEnum = (SendTypeEnum)pInput;
                    return sendTypeEnum.name();
                }
            });
            result.append(BRACKET_OPEN);
            result.append(StringUtils.join(teilsendToken.iterator(), SEP));
            result.append(BRACKET_CLOSE);
            if (hasAuftragVorsendung()) {
                result.append(TOKEN_VORSENDUNG_START);
                Iterator<SendTypeEnum> iterator = getAuftragVorsendungen().iterator();
                result.append(StringUtils.join(iterator, SEP));
                result.append(BRACKET_CLOSE);
            }
            return result.toString();
        }

        public Date getSendeterminForHist() {
            Date calculateSendetermin =
                CalcSendeterminForHistUtil.calculateSendeterminHistForSendung(auftrag, processStartTimestamp);
            return calculateSendetermin;
        }

        private boolean hasAuftragVorsendung() {
            return (getAuftragVorsendungen().size() > 0);
        }

        private List<SendTypeEnum> getAuftragVorsendungen() {
            if (vorsendungenList == null) {
                createVorsendungenList();
            }
            return vorsendungenList;
        }

        private void createVorsendungenList() {
            vorsendungenList = new ArrayList<SendTypeEnum>();
            Map<SendTypeEnum, SendStatusEnum> sendStatusIstMap = AuftragUtil.getSendStatusIstMap(auftrag);
            Set<Entry<SendTypeEnum, SendStatusEnum>> entrySet = sendStatusIstMap.entrySet();
            CollectionUtils.filter(entrySet, new Predicate() {
                @Override
                public boolean evaluate(Object pObject) {
                    @SuppressWarnings("unchecked")
                    Entry<SendTypeEnum, SendStatusEnum> entry = (Entry<SendTypeEnum, SendStatusEnum>)pObject;
                    // not gesendet -> remove from collection --> only 'gesendet'e
                    return entry.getValue().isGesendet();
                }
            });
            for (Entry<SendTypeEnum, SendStatusEnum> entry : entrySet) {
                SendTypeEnum sendTypeGesendet = entry.getKey();
                if (!performedSendTypes.contains(sendTypeGesendet)) {
                    vorsendungenList.add(sendTypeGesendet);
                }
            }
            Collections.sort(vorsendungenList, new ProcessIdComparator<SendTypeEnum>());
        }
    }

    /**
     * Private inner class that prepares the data for a history entry.
     * @author THB
    //        Es wird f r die angestossene Benutzeraktion (z.B.  Komplettstornierung ) genau ein Historieneintrag nach folgendem Inhalt erstellt.
    //        <Aktionstyp> <Aktion> (<TeilsendungstypA>:<Storno | Ausfall>, )
    //        Beispiele daf r:
    //        Komplettstornierung   durchgef hrte Teilstornos LMT (als Ausfall), RHM (als Ausfall), FHI (als Stornierung), UBM (als Stornierung)
    //         Storno kompl. (LMT:A, RHM:A, UBM:S, FHI:S) 
    //
    //        Teilstornierung FHI   durchgef hrte Teilstornos FHI (als Ausfall), LMT (als Stornierung)
    //         Storno FHI  (FHI:A, LMT:S) 
    //        Anmerkung:
    //        Information  Stornierung mit Vor-Storno  analog zur Sendung gibt es nicht.
     *
     */
    private class StornoMessageFactory {
        private static final String MELDER_STOR = "STOR";

        private static final String COLON = ":";

        private static final String BRACKET_OPEN = "(";

        private static final String BRACKET_CLOSE = ")";

        private static final String STORNO_SEP = ",";

        private final SendTypeEnum userAction;

        private final Auftrag auftrag;

        private final Map<SendTypeEnum, CancelSendTypeEnum> performedStornoTypes;

        private final Date processStartTimestamp;

        protected StornoMessageFactory(SendTypeEnum pUserAction, Auftrag pAuftrag,
                Map<SendTypeEnum, CancelSendTypeEnum> pPerformedStornoTypes, Date pProcessStartTimestamp) {
            super();
            userAction = pUserAction;
            auftrag = pAuftrag;
            performedStornoTypes = pPerformedStornoTypes;
            processStartTimestamp = pProcessStartTimestamp;
        }

        public Date getStornoterminForHist() {
            Date calculateSendetermin =
                CalcSendeterminForHistUtil.calculateSendeterminHistForStorno(auftrag, processStartTimestamp);
            return calculateSendetermin;
        }

        protected String getMessage() {
            String meldungUserAction = getMeldungStartForStornoUserAction(userAction);
            String fhi = getStornoPerformed(SendTypeEnum.FHI, performedStornoTypes);
            String lmt = getStornoPerformed(SendTypeEnum.LMT, performedStornoTypes);
            String ubm = getStornoPerformed(SendTypeEnum.UBM, performedStornoTypes);
            String rhm = getStornoPerformed(SendTypeEnum.RHM, performedStornoTypes);

            StringBuilder meldung = new StringBuilder(meldungUserAction);
            meldung.append(BasisStringUtils.SINGLE_BLANK);
            meldung.append(BRACKET_OPEN);
            List<String> stornoDetails = new ArrayList<String>();
            if (!BasisStringUtils.isEmptyOrNull(fhi)) {
                stornoDetails.add(fhi);
            }
            if (!BasisStringUtils.isEmptyOrNull(lmt)) {
                stornoDetails.add(lmt);
            }
            if (!BasisStringUtils.isEmptyOrNull(ubm)) {
                stornoDetails.add(ubm);
            }
            if (!BasisStringUtils.isEmptyOrNull(rhm)) {
                stornoDetails.add(rhm);
            }
            meldung.append(StringUtils.join(stornoDetails.iterator(), STORNO_SEP));
            meldung.append(BRACKET_CLOSE);

            return meldung.toString();
        }

        protected String getMeldekenn() {
            switch (userAction) {
                case KOMPLETT:
                    return "KST";
                case FHI:
                case LMT:
                case RHM:
                case UBM:
                    return userAction.name();
                default:
                    throw new RuntimeException("Invalid user action for storno: " + userAction);
            }
        }

        protected String getMelder() {
            return MELDER_STOR;
        }

        private String getMeldungStartForStornoUserAction(SendTypeEnum pEnum) {
            switch (pEnum) {
                case KOMPLETT:
                    return "Storno kompl.";
                case FHI:
                    return "Storno teil. FHI";
                case LMT:
                    return "Storno teil. LMT";
                case RHM:
                    return "Storno teil. RHM";
                case UBM:
                    return "Storno teil. UBM";
                default:
                    throw new RuntimeException("Invalid user action for storno: " + pEnum);
            }
        }

        private String getStornoPerformed(SendTypeEnum pRequestType,
                Map<SendTypeEnum, CancelSendTypeEnum> pPerformedStornoTypes) {
            String result = StringUtils.EMPTY;
            if (pPerformedStornoTypes.containsKey(pRequestType)) {
                CancelSendTypeEnum cancelSendTypeEnum = pPerformedStornoTypes.get(pRequestType);
                String stornoCode = cancelSendTypeEnum.getCode();
                result = pRequestType.name() + COLON + stornoCode;
            }
            return result;
        }
    }
}
