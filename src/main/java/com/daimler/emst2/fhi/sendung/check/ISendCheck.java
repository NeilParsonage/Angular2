package com.daimler.emst2.fhi.sendung.check;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.check.ICheck;

public interface ISendCheck extends ICheck<SendPreconditionEnum, SendCheckEnum, SendContext> {
}
