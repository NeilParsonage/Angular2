package com.daimler.emst2.fhi.sendung.comparators;

import java.util.Comparator;

import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;

public class AuftragAnkuendigungenComparator implements Comparator<AuftragSperrInformation> {

    @SuppressWarnings("unchecked")
	private static final Comparator<String> STRING_COMPERATOR = ComparatorUtils.nullHighComparator(ComparatorUtils.NATURAL_COMPARATOR);

    @SuppressWarnings("unchecked")
    private static final Comparator<Integer> INTEGER_COMPERATOR = ComparatorUtils.nullHighComparator(ComparatorUtils.NATURAL_COMPARATOR);
    
    /**
     * <pre>
     * Die Darstellung der Ank ndigungen im Popup erfolgt sortiert nach
     *  	Sortierkriterium 1 (Herkunft):
     * 	o	ASP-Ank ndigungen
     * 	o	Host, Eilt-Kennzeichen, FHI-Forms, FHI-Ank ndigungen
     *  	Sortierkriterium 2:
     * 	o	Innerhalb dieser beiden Gruppen erfolgt die Sortierung nach dem Sperrcode, dann nach Sperrgrund - alphanumerisch aufsteigen
     * </pre>
     */
    @Override
    public int compare(AuftragSperrInformation pAnkuendigung1, AuftragSperrInformation pAnkuendigung2) {
        Assert.notNull(pAnkuendigung1);
        Assert.notNull(pAnkuendigung2);
        
        // 1. Order group
        int cmp = INTEGER_COMPERATOR.compare(pAnkuendigung1.getOrderGroup(), pAnkuendigung2.getOrderGroup());
        if (cmp != 0) {
        	return cmp;
        }
        
        // 2. Sperrcode
        cmp = STRING_COMPERATOR.compare(pAnkuendigung1.getSperrcode(), pAnkuendigung2.getSperrcode());
        if (cmp != 0) {
        	return cmp;
        }
        
        // 3. Sperrgrund
        return cmp = STRING_COMPERATOR.compare(pAnkuendigung1.getSperrgrund(), pAnkuendigung2.getSperrgrund());
    }
}
