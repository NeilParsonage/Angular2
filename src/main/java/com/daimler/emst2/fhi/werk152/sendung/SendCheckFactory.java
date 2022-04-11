package com.daimler.emst2.fhi.werk152.sendung;

import com.daimler.emst2.fhi.sendung.check.CheckAuftragAnkuendigung;
import com.daimler.emst2.fhi.sendung.check.CheckAuftragSperren;
import com.daimler.emst2.fhi.sendung.check.CheckAuftragUpToDate;
import com.daimler.emst2.fhi.sendung.check.CheckFhiImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.check.CheckFhiOrtInvalid;
import com.daimler.emst2.fhi.sendung.check.CheckFhiSollabstand;
import com.daimler.emst2.fhi.sendung.check.CheckLmtOrtInvalid;
import com.daimler.emst2.fhi.sendung.check.CheckLmtSollabstand;
import com.daimler.emst2.fhi.sendung.check.CheckRhmImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.check.CheckRhmOrtInvalid;
import com.daimler.emst2.fhi.sendung.check.CheckRhmSollabstand;
import com.daimler.emst2.fhi.sendung.check.CheckUbmImpliziteTeilsendung;
import com.daimler.emst2.fhi.sendung.check.CheckUbmOrtInvalid;
import com.daimler.emst2.fhi.sendung.check.CheckUbmSollabstand;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.process.check.ICheck;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.protocol.ProtocolService;

public class SendCheckFactory implements ICheckFactory<SendCheckEnum> {

    private ProtocolService protocolService;

	@Override
    @SuppressWarnings("rawtypes")
	public ICheck createCheck(SendCheckEnum pruefungKennung) {
		switch (pruefungKennung) {
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
		this.protocolService = protocolService;
	}
}
