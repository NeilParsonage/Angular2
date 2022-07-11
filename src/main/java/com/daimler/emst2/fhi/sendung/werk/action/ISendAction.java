package com.daimler.emst2.fhi.sendung.werk.action;

import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.werk.precondition.SendPreconditionEnum;

public interface ISendAction extends IAction<SendPreconditionEnum, SendActionEnum, SendContext> {

}
