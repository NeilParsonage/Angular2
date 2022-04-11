package com.daimler.emst2.fhi.sendung.processcommon;

import java.util.Map;
import java.util.Set;

import com.daimler.emst2.fhi.sendung.constants.OrtCheckEnum;
import com.daimler.emst2.fhi.sendung.constants.OrtTypEnum;

public interface IOrtsdatenProcessContext {

    public Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> getOrtChecksMap();

    public void setOrtChecksMap(Map<OrtCheckEnum, Map<OrtTypEnum, Set<String>>> pOrtChecksMap);
}
