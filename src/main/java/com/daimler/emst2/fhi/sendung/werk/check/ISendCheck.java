package com.daimler.emst2.fhi.sendung.werk.check;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.check.ICheck;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public interface ISendCheck extends ICheck<SendPreconditionEnum, SendCheckEnum, SendContext> {
}
