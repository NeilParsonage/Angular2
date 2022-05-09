package com.daimler.emst2.fhi.sendung.process;

import java.util.List;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;

public interface IProcess<GenType extends ITuebObject, GenCheckEnum extends IProcessId, GenActionEnum extends IProcessId> {

    /**
     * Fuegt einen Kindknoten hinzu.
     */
    public <A extends IProcess<GenType, GenCheckEnum, GenActionEnum>> void addChild(A entry);

    /**
     * Liefert die strukturellen direkten Kindknoten. Wird zur Traversierung
     * der Struktur benoetigt.
     */
    public List<? extends IProcess<GenType, GenCheckEnum, GenActionEnum>> getChildren();

    /**
     * Liefert rekursiv die Liste aller erreichbaren Kindknoten und den Knoten selbst.
     */
    public List<? extends IProcess<GenType, GenCheckEnum, GenActionEnum>> getSubtreeFlat();

    /**
     * Liefert true, wenn der Knoten keine Kinder hat - also ein Blatt des Baumes darstellt.
     * @return
     */
    public boolean isLeaf();
	
    /**
     * Gibt den Typ z.B. SendTypeEnum zurueck
     */
    public GenType getType();

    /**
     * Liefert alle Pruefungen die an dieser Teilsendung registriert sind.
     * 
     * Um rekusiv alle Pruefungen zu dieser Teilsung mit in der Hierarchie
     * untergeordneten Teilsendungen zu bekommen @see getAllPruefungen
     * 
     * @return Liste aller Pruefungen dieser Teilsendung
     */
    public List<GenCheckEnum> getChecks();

    /**
     * Liefert eine Liste aller Aktionen die an dieser Teilsendung registriert
     * sind
     * 
     * Um rekursiv alle Aktionen zu dieser Teilsendung mit in der Hirarchie
     * untergeordneten Teilsendungen zu bekommen @see getAllAktionen
     * 
     * @return Liste aller Aktionen dieser Teilsendung
     */
    public List<GenActionEnum> getActions();

}