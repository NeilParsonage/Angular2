package com.daimler.emst2.fhi.sendung.process;

import java.util.List;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;

public interface IProcessConfiguration<GenTypeEnum extends ITuebObject, GenCheckEnum extends IProcessId, GenActionEnum extends IProcessId> {

    public GenTypeEnum getType();

    public void fillCheckEnumList(List<GenCheckEnum> pCheckList);

    public void fillActionEnumList(List<GenActionEnum> pActionList);
}
