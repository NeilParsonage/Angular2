package com.daimler.emst2.fhi.sendung.process.comparator;

import java.util.Comparator;

import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.sendung.process.action.IAction;

@SuppressWarnings("rawtypes")
public class ProcessActionComparator implements Comparator<IAction> {

    private static final Comparator<Integer> INTEGER_COMPERATOR = ComparatorUtils.nullLowComparator(ComparatorUtils.NATURAL_COMPARATOR);

    @Override
    public int compare(IAction pO1, IAction pO2) {
        Assert.notNull(pO1);
        Assert.notNull(pO2);
        IProcessId action1Id = pO1.getIdentifier();
        IProcessId action2Id = pO2.getIdentifier();
        Integer order1 = action1Id.getOrderNum();
        Integer order2 = action2Id.getOrderNum();
        return INTEGER_COMPERATOR.compare(order1, order2);
    }
}
