package com.daimler.emst2.fhi.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * *
 * @author bjb, Opitz Consulting Bad Homburg GmbH
 */
public final class BasisCollectionUtils {

    @SuppressWarnings("rawtypes")
    private static final class UniqueKeyHashMapWrapper implements Map {
        private final Map wrappedMap;

        UniqueKeyHashMapWrapper(final Map pWrappedMap) {
            super();
            this.wrappedMap = pWrappedMap;
        }

        @Override
        public void clear() {
            this.wrappedMap.clear();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.wrappedMap.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.wrappedMap.containsValue(value);
        }

        @Override
        public Set entrySet() {
            return this.wrappedMap.entrySet();
        }

        @Override
        public boolean equals(Object o) {
            return this.wrappedMap.equals(o);
        }

        @Override
        public Object get(Object key) {
            return this.wrappedMap.get(key);
        }

        @Override
        public int hashCode() {
            return this.wrappedMap.hashCode();
        }

        @Override
        public boolean isEmpty() {
            return this.wrappedMap.isEmpty();
        }

        @Override
        public Set keySet() {
            return this.wrappedMap.keySet();
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object put(Object key, Object value) {
            /*
             * Extra Prüfung, weil beim doppelten einfügen des Keys 'null'
             * beim zweiten Einfügen auch null liefern würde, darum reicht der
             * Rückgabewert von put nicht aus.
             */
            if (containsKey(key)) {
                throw new RuntimeException("Key ist in der Map scon vorhanden! (" + key + ") [" + this + "]");
            }
            return this.wrappedMap.put(key, value);
        }

        /*
         * Fügt alle Elemente einzeln ein, da nicht sichergestellt werden kann,
         * dass alle Impelentierungen von Map beim Einfügen auch put
         * verwenden.
         * HashMap würde hier kein Überschreiben erforder, das es intern put zum
         * Einfügen verwendet.
         * @see java.util.Map#putAll(java.util.Map)
         */
        @Override
        public void putAll(Map t) {
            for (Iterator iter = t.entrySet().iterator(); iter.hasNext();) {
                Map.Entry entry = (Map.Entry)iter.next();
                put(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public Object remove(Object key) {
            return this.wrappedMap.remove(key);
        }

        @Override
        public int size() {
            return this.wrappedMap.size();
        }

        @Override
        public Collection values() {
            return this.wrappedMap.values();
        }

        @Override
        public String toString() {
            return this.wrappedMap + " [" + super.toString() + "]";
        }
    }

    /**
     * Liefert einen Wrapper für die Map in der das einfügen von Elementen
     * über put und putAll zu Fehlern führt, wenn der Key schon vorhanden ist.
     * Die Ursprüngliche Map kann über put und putAll weierhin ohne Fehler beim
     * Einfügen verwendet werden.
     * @param wrappedMap
     * @return
     * @see java.util.Map
     */
    public static Map createUniqueKeyMap(Map wrappedMap) {
        return new UniqueKeyHashMapWrapper(wrappedMap);
    }

    private BasisCollectionUtils() {
        //
    }

    public static final boolean isEmptyOrNull(Collection collection) {
        return collection == null ? true : collection.isEmpty();
    }

    public static final boolean isEmptyOrNull(Map map) {
        return map == null ? true : map.isEmpty();
    }

    /**
     * Converts a given list of string values to a comma separated string that
     * may be used in an sql statement as a value for a in-clause.
     * 
     * @param pList
     * @return
     */
    public static final String stringList2sqlInArgument(List pList) {
        StringBuffer sb = new StringBuffer();
        Iterator iter = pList.iterator();
        while (iter.hasNext()) {
            String elem = (String)iter.next();
            sb.append("'");
            sb.append(elem);
            sb.append("'");
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Puts all public static fields into the resulting Map. Uses the name of
     * the field as key to reference it's in the Map.
     * 
     * @return a Map of field names to field values of all public static fields
     *         of this class
     */
    public static Map<String, Object> createNameToValueMap(Class<?> clazzObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] publicFields = clazzObject.getFields();
        for (int i = 0; i < publicFields.length; i++) {
            Field field = publicFields[i];
            String name = field.getName();
            try {
                map.put(name, field.get(null));
            } catch (Exception e) {
                System.err.println("Initialization of Constants of class '" + clazzObject.getName() + "' failed!");
                e.printStackTrace(System.err);
            }
        }
        return map;
    }
}
