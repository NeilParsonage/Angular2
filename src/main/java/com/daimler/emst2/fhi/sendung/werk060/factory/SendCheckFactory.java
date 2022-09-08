package com.daimler.emst2.fhi.sendung.werk060.factory;

import com.daimler.emst2.fhi.sendung.process.check.ICheck;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.CheckAuftragUpToDate;
import com.daimler.emst2.fhi.sendung.werk.check.CheckDummyToImplement;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckAuftragSperren;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiAnzahlFreie;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiAnzahlGasse4;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiErreichbarkeit;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiGassensperre;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiSeqNrObergrenze;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckFhiVorsendungen;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckLmtOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckRhmOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckUmlaufwerte;
import com.daimler.emst2.fhi.sendung.werk060.check.CheckWarteschlange;

public class SendCheckFactory implements ICheckFactory<SendCheckEnum> {

    private final ProtocolService protocolService;

    private SendCheckFactory(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    public static ICheckFactory<SendCheckEnum> create(ProtocolService protocolService) {
        return new SendCheckFactory(protocolService);
    }

	@Override
    @SuppressWarnings("rawtypes")
	public ICheck createCheck(SendCheckEnum pruefungKennung) {
		switch (pruefungKennung) {
            //TODO NEP Review the AUFTRAG_UPTODATE implementation currently locks the Auftrag
            // and therefore MUST appear as the first Step !! 
		case AUFTRAG_UPTODATE:
            return new CheckAuftragUpToDate(getProtocolService());
		case AUFTRAG_ANKUENDIGUNG_VORHANDEN:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckAuftragAnkuendigung(getProtocolService());
		case AUFTRAG_SPERRE_VORHANDEN:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckAuftragSperren(getProtocolService());
		case FHI_IMPLIZITE_TEILSENDUNG:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckFhiImpliziteTeilsendung(getProtocolService());
		case RHM_IMPLIZITE_TEILSENDUNG:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckRhmImpliziteTeilsendung(getProtocolService());
		case LMT_SOLLABSTAND:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckLmtSollabstand(getProtocolService());
		case RHM_SOLLABSTAND:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckRhmSollabstand(getProtocolService());
		case FHI_SOLLABSTAND:
            return new CheckDummyToImplement(getProtocolService());
            // return new CheckFhiSollabstand(getProtocolService());
		case FHI_ORT_ERR:
            return new CheckFhiOrtInvalid(getProtocolService());
        case LMT_ORT_ERR:
            return new CheckLmtOrtInvalid(getProtocolService());
		case RHM_ORT_ERR:
			return new CheckRhmOrtInvalid(getProtocolService());
        case WARTESCHLANGE_060:
            return new CheckWarteschlange(getProtocolService(), pruefungKennung);
        case FHI_VORSENDUNGEN_060:
            return new CheckFhiVorsendungen(getProtocolService(), pruefungKennung);
        case FHI_ERREICHBARKEIT_060:
            return new CheckFhiErreichbarkeit(getProtocolService(), pruefungKennung);
        case FHI_UMLAUFGRENZE_UEBERSCHRITTEN_060:
            return new CheckUmlaufwerte(getProtocolService(), pruefungKennung);
        case RHM_UMLAUFGRENZE_UEBERSCHRITTEN_060:
            return new CheckUmlaufwerte(getProtocolService(), pruefungKennung);
        case FHI_SEQNR_OBERGRENZE_060:
            return new CheckFhiSeqNrObergrenze(getProtocolService(), pruefungKennung);
        case FHI_GASSENSPERRE_060:
            return new CheckFhiGassensperre(getProtocolService(), pruefungKennung);
        case FHI_GASSE_4_060:
            return new CheckFhiAnzahlGasse4(getProtocolService(), pruefungKennung);
        case AUFTRAG_ANZAHL_FREIE_060:
            return new CheckFhiAnzahlFreie(getProtocolService());
        case FHI_AUFTRAG_SPERREN_FUER_BEREICH_060:
            return new CheckAuftragSperren(getProtocolService(), pruefungKennung);
        case RHM_AUFTRAG_SPERREN_FUER_BEREICH_060:
            return new CheckAuftragSperren(getProtocolService(), pruefungKennung);
		default:
			throw new RuntimeException("Unsupported value of SendCheckEnum: " + pruefungKennung);
		}
	}

    private ProtocolService getProtocolService() {
		return protocolService;
	}

    public void setProtocolService(ProtocolService protocolService) {
        // deprecated - new implementation
	}
}
