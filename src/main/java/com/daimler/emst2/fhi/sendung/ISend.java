package com.daimler.emst2.fhi.sendung;

import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcess;
import com.daimler.emst2.fhi.sendung.werk.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.werk.check.SendCheckEnum;

/**
 * SendeHierarchieEntry für Komplett/Teilsendungen.
 * 
 * @author thi
 * 
 */
public interface ISend extends IProcess<SendTypeEnum, SendCheckEnum, SendActionEnum> {

}
