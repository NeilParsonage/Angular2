package com.daimler.emst2.fhi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.util.Assert;

/*
 * *
 * @author bjb, Opitz Consulting Bad Homburg GmbH
 */
public final class BasisStringUtils {

    public static final String SINGLE_BLANK = " ";

    public static final String SINGLE_MINUS = "-";

    public static final String BLANK_MINUS_BLANK = " - ";

    public static final String HTML_NBSP = "&nbsp;";

    private BasisStringUtils() {
        //
    }

    public static boolean isEmptyOrNull(String pObj) {
        return StringUtils.isBlank(pObj);
    }

    public static String convertToString(Integer pInteger) {
        return convertToString(pInteger, SINGLE_BLANK);
    }

    public static String convertToString(Integer pInteger, String defaultValue) {
        if (pInteger == null) {
            return defaultValue;
        }
        return pInteger.toString();
    }

    public static String getSubstringBetween(String tokenStart, String tokenEnd, String originalString) {
        int len1 = isEmptyOrNull(tokenStart) ? 0 : tokenStart.length();
        int len2 = isEmptyOrNull(tokenEnd) ? 0 : tokenEnd.length();

        int ind1 = 0;
        int ind2 = originalString.length();

        if ((len1 > 0) && (originalString.indexOf(tokenStart) >= 0)) {
            ind1 = originalString.indexOf(tokenStart) + len1;
        }
        if ((len2 > 0) && (originalString.indexOf(tokenEnd) > 0)) {
            ind2 = originalString.indexOf(tokenEnd);
        }

        return originalString.substring(ind1, ind2);
    }

    public static String getFixedSizeString(String pData, int pSize) {
        Assert.notNull(pData);
        Assert.isTrue(pData.length() <= pSize);
        String resultString = StringUtils.rightPad(pData, pSize);
        return resultString;
    }

    /**
     * Bis auf eine Besonderheit identisch zu {@link StringUtils#leftPad(String, int, String)}.
     * Besonderheit: wird als Parameter str 'null' übergeben, so wird dies wie ein Leerstring interpretiert.
     * @param str
     * @param size
     * @param padStr
     * @return
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            str = StringUtils.EMPTY;
        }
        return StringUtils.leftPad(str, size, padStr);
    }

    /**
     * Verwendet z.B. f. die Favoritenfilter
     * 
     * @param sourceString
     * @param delimiters
     * @return
     */
    public static List<String> getSplittedStringWithWhitespaceSplit(String sourceString, String delimiters) {
        List<String> result = getSplittedString(sourceString, delimiters);
        if (result != null) {
            Iterator<String> iter = result.iterator();
            List<String> newResult = new ArrayList<String>(result.size());
            while (iter.hasNext()) {
                String element = iter.next();
                if (!"".equals(element.trim())) {
                    newResult.add(element);
                }
            }
            result = newResult;
        }
        return result;
    }

    /**
     * Verwendet z.B. f. die Favoritenfilter
     * 
     * @param sourceString
     * @param delimiters
     * @return
     */
    public static List<String> getSplittedString(String sourceString, String delimiters) {
        Assert.notNull(delimiters);
        Assert.notNull(sourceString);
        String delimitersWork = "[" + delimiters + "]";
        String[] res = sourceString.split(delimitersWork);
        List<String> result = Arrays.asList(res);
        return result;
    }

    /**
     * <pre>
     * BasisStringUtils.convertToBeanName(&quot;&quot;)                        = &quot;&quot;
     * BasisStringUtils.convertToBeanName(&quot;ein kleiner TEST&quot;)        = &quot;EinKleinerTEST&quot;
     * BasisStringUtils.convertToBeanName(&quot;EinKleinerTEST&quot;)          = &quot;EinKleinerTEST&quot;
     * BasisStringUtils.convertToBeanName(&quot;   ein kleiner TEST   &quot;)  = &quot;EinKleinerTEST&quot;
     * BasisStringUtils.convertToBeanName(&quot; mal.ein kleiner_TEST  &quot;) = &quot;MalEinKleinerTEST&quot;
     * </pre>
     * 
     * @param name
     * @return
     * @throws NullPointerException wenn <code>name==null</code> ist.
     */
    public static String convertToBeanName(String name) {
        return WordUtils.capitalize(name.trim(), new char[] { ' ', '.', '_' }).replaceAll("[ /._]", "");
    }

    /**
     * <pre>
     * BasisStringUtils.convertToBeanNameFirstLow(&quot;&quot;)                        = &quot;&quot;
     * BasisStringUtils.convertToBeanNameFirstLow(&quot;ein kleiner TEST&quot;)        = &quot;einKleinerTEST&quot;
     * BasisStringUtils.convertToBeanNameFirstLow(&quot;EinKleinerTEST&quot;)          = &quot;einKleinerTEST&quot;
     * BasisStringUtils.convertToBeanNameFirstLow(&quot;   ein kleiner TEST   &quot;)  = &quot;einKleinerTEST&quot;
     * BasisStringUtils.convertToBeanNameFirstLow(&quot; mal.ein kleiner_TEST  &quot;) = &quot;malEinKleinerTEST&quot;
     * </pre>
     * 
     * @param name
     * @return
     * @throws NullPointerException wenn <code>name==null</code> ist.
     */
    public static String convertToBeanNameFirstLow(String name) {
        return StringUtils.uncapitalize(convertToBeanName(name));
    }

    /**
     * Returns either the passed in String, or if the String is null, an empty String ("").
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * @param str
     * @return
     */
    public static String defaultString(String str) {
        return StringUtils.defaultString(str);
    }

    /**
     * Designed for DB-VORGANGS-MELDUNGEN!
     * Parse given DB-PARAM-String and return String[] with parsed values.
     * 
     * Example:
     * IN: ",a,b,c"
     * OUT: { "a", "b", "c" }
     * 
     * Zerlegt den String ein einzelne Strings, wobei das erste
     * Zeichen als Trennzeichen verwendet wird.
     * 
     * @param string to parse
     * @return string array containing the parsed strings
     */
    public static String[] convertVorgangParamstring(String param) {
        // Wenn das String leer oder null ist, wird null geliefert.
        if (BasisStringUtils.isEmptyOrNull(param)) {
            return null;
        }

        // Seperator ermitteln
        final char seperator = param.charAt(0);
        final String stringSeperator = String.valueOf(seperator);
        final String doppelStringSeperator = stringSeperator + stringSeperator;

        // Restlichen String ermitteln und am Seperator zerlegen.
        final String[] result = StringUtils.split(param.substring(1), seperator);

        for (int i = 0; i < result.length; i++) {
            /*
             * Warum nicht:
             * result[i].replaceAll(doppelStringSeperator, stringSeperator);
             * Weil replaceAll regular expression verwendet und wir nicht pruefen,
             * ob der Seperator ein von regular expression verwendetes
             * Zeichen ist. (Beispiel: [, \, *, ...)
             */
            result[i] = StringUtils.replace(result[i], doppelStringSeperator, stringSeperator);
        }
        return result;
    }

}
