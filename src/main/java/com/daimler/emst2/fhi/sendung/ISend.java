package com.daimler.emst2.fhi.sendung;

import com.daimler.emst2.fhi.sendung.action.SendActionEnum;
import com.daimler.emst2.fhi.sendung.check.SendCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.SendTypeEnum;
import com.daimler.emst2.fhi.sendung.process.IProcess;

/**
 * SendeHierarchieEntry für Komplett/Teilsendungen.
 * 
 * @author thi
 * 
 */
public interface ISend extends IProcess<SendTypeEnum, SendCheckEnum, SendActionEnum> {

}
