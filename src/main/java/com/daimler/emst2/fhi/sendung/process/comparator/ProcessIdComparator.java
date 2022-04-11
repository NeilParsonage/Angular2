package com.daimler.emst2.fhi.sendung.process.comparator;

import java.util.Comparator;

import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IProcessId;

public class ProcessIdComparator<T extends IProcessId> implements Comparator<T> {

    @SuppressWarnings("unchecked")
    private static final Comparator<Integer> INTEGER_COMPERATOR = ComparatorUtils.nullLowComparator(ComparatorUtils.NATURAL_COMPARATOR);

    @Override
    public int compare(IProcessId pO1, IProcessId pO2) {
        Assert.notNull(pO1);
        Assert.notNull(pO2);
        Integer order1 = pO1.getOrderNum();
        Integer order2 = pO2.getOrderNum();
        return INTEGER_COMPERATOR.compare(order1, order2);
    }
}