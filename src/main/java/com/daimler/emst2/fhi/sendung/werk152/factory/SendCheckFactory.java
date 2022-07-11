package com.daimler.emst2.fhi.sendung.werk152.factory;

import com.daimler.emst2.fhi.sendung.process.check.ICheck;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;
import com.daimler.emst2.fhi.sendung.werk.check.CheckAuftragAnkuendigung;
import com.daimler.emst2.fhi.sendung.werk.check.CheckAuftragUpToDate;
import com.daimler.emst2.fhi.sendung.werk.check.CheckFhiImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.werk.check.CheckFhiOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk.check.CheckFhiSollabstand;
import com.daimler.emst2.fhi.sendung.werk.check.CheckLmtOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk.check.CheckLmtSollabstand;
import com.daimler.emst2.fhi.sendung.werk.check.CheckRhmImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.werk.check.CheckRhmOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk.check.CheckRhmSollabstand;
import com.daimler.emst2.fhi.sendung.werk.check.CheckUbmImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.werk.check.CheckUbmOrtInvalid;
import com.daimler.emst2.fhi.sendung.werk.check.CheckUbmSollabstand;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.werk152.check.CheckAuftragSperren;

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
			return new CheckAuftragAnkuendigung(getProtocolService());
		case AUFTRAG_SPERRE_VORHANDEN:
			return new CheckAuftragSperren(getProtocolService());
		case FHI_IMPLIZITE_TEILSENDUNG:
			return new CheckFhiImpliziteTeilsendung(getProtocolService());
		case UBM_IMPLIZITE_TEILSENDUNG:
			return new CheckUbmImpliziteTeilsendung(getProtocolService());
		case RHM_IMPLIZITE_TEILSENDUNG:
			return new CheckRhmImpliziteTeilsendung(getProtocolService());
		case LMT_SOLLABSTAND:
			return new CheckLmtSollabstand(getProtocolService());
		case RHM_SOLLABSTAND:
			return new CheckRhmSollabstand(getProtocolService());
		case UBM_SOLLABSTAND:
			return new CheckUbmSollabstand(getProtocolService());
		case FHI_SOLLABSTAND:
			return new CheckFhiSollabstand(getProtocolService());
		case FHI_ORT_ERR:
			return new CheckFhiOrtInvalid(getProtocolService());
		case LMT_ORT_ERR:
			return new CheckLmtOrtInvalid(getProtocolService());
		case RHM_ORT_ERR:
			return new CheckRhmOrtInvalid(getProtocolService());
		case UBM_ORT_ERR:
			return new CheckUbmOrtInvalid(getProtocolService());
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
