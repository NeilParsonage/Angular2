package com.daimler.emst2.fhi.sendung;

import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcessConfiguration;
import com.daimler.emst2.fhi.sendung.process.Process;

public class SendProcess extends Process<SendTypeEnum, SendActionEnum, SendCheckEnum> implements ISend {

	public SendProcess(IProcessConfiguration<SendTypeEnum, SendCheckEnum, SendActionEnum> pProcessConfiguration) {
	    super(pProcessConfiguration);
	}
	
}
