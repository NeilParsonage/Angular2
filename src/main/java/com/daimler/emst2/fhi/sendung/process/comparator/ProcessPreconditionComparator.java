package com.daimler.emst2.fhi.sendung.process.comparator;

import java.util.Comparator;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.precondition.IPrecondition;

public class ProcessPreconditionComparator<T extends IPrecondition> implements Comparator<T> {

    private static final Comparator<IProcessId> PROCESSID_COMPERATOR = new ProcessIdComparator();

    @Override
    public int compare(T pO1, T pO2) {
        Assert.notNull(pO1);
        Assert.notNull(pO2);
        IProcessId identifier1 = pO1.getIdentifier();
        IProcessId identifier2 = pO2.getIdentifier();
        return PROCESSID_COMPERATOR.compare(identifier1, identifier2);
    }
}
