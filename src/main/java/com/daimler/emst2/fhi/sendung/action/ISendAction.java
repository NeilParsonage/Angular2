package com.daimler.emst2.fhi.sendung.action;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.precondition.SendPreconditionEnum;
import com.daimler.emst2.fhi.sendung.process.action.IAction;

public interface ISendAction extends IAction<SendPreconditionEnum, SendActionEnum, SendContext> {

}
