package com.daimler.emst2.fhi.sendung.comparators;

import java.util.Comparator;

import org.apache.commons.collections4.ComparatorUtils;
import org.springframework.util.Assert;

import com.daimler.emst2.fhi.jpa.model.AuftragSperrInformation;


public class AuftragSperrenComparator implements Comparator<AuftragSperrInformation> {

    @SuppressWarnings("unchecked")
    private static final Comparator<String> STRING_COMPERATOR = ComparatorUtils.nullHighComparator(ComparatorUtils.NATURAL_COMPARATOR);

    @SuppressWarnings("unchecked")
    private static final Comparator<Integer> INTEGER_COMPERATOR = ComparatorUtils.nullHighComparator(ComparatorUtils.NATURAL_COMPARATOR);
    
    @SuppressWarnings("unchecked")
	private static final Comparator<Boolean> BOOLEAN_COMPERATOR = ComparatorUtils.booleanComparator(true);
    
    /**
     * <pre>
     * Sortierkriterien fuer Sperren: 
     * 	 	Sortierkriterium 1 (Herkunft):
     * 		o	"relevante" ASP-Sperren
     * 		o	"nicht relevante" ASP-Sperren
     * 		o	Host-, FHI-Forms- und FHI-Sperren
     * 	 	Innerhalb dieser drei Gruppen erfolgt die Sortierung nach dem 
     * 		o	Sperrcode, dann nach 
     * 		o	Sperrgrund - alphanumerisch aufsteigend
     * </pre>
     */
    @Override
    public int compare(AuftragSperrInformation pSperre1, AuftragSperrInformation pSperre2) {
        Assert.notNull(pSperre1);
        Assert.notNull(pSperre2);
        
        // 1. Order group
        int cmp = INTEGER_COMPERATOR.compare(pSperre1.getOrderGroup(), pSperre2.getOrderGroup());
        if (cmp != 0) {
        	return cmp;
        }
        
        // 2. relevant - nicht relevant
        cmp = BOOLEAN_COMPERATOR.compare(pSperre1.getRelevantKnz(), pSperre2.getRelevantKnz());
        if (cmp != 0) {
        	return cmp;
        }
        
        // 3. Sperrcode
        cmp = STRING_COMPERATOR.compare(pSperre1.getSperrcode(), pSperre2.getSperrcode());
        if (cmp != 0) {
        	return cmp;
        }
        
        // 4. Sperrgrund
        return cmp = STRING_COMPERATOR.compare(pSperre1.getSperrgrund(), pSperre2.getSperrgrund());
    }
}
