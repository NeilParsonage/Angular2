package com.daimler.emst2.fhi.sendung.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.Protocol;
import com.daimler.emst2.fhi.model.SeverityEnum;
import com.daimler.emst2.fhi.sendung.model.SendContext;
import com.daimler.emst2.fhi.sendung.process.action.IAction;
import com.daimler.emst2.fhi.sendung.process.action.IActionFactory;
import com.daimler.emst2.fhi.sendung.process.check.ICheck;
import com.daimler.emst2.fhi.sendung.process.check.ICheckFactory;
import com.daimler.emst2.fhi.sendung.process.comparator.ProcessActionComparator;
import com.daimler.emst2.fhi.sendung.process.comparator.ProcessPreconditionComparator;
import com.daimler.emst2.fhi.sendung.process.precondition.IPrecondition;
import com.daimler.emst2.fhi.sendung.process.precondition.IPreconditionFactory;
import com.daimler.emst2.fhi.sendung.protocol.IProtocolService;
import com.daimler.emst2.fhi.util.BasisObjectUtil;


@SuppressWarnings("rawtypes")
public abstract class AbstractProcessService<GenPreconditionEnum extends IProcessId, GenCheckEnum extends IProcessId, GenActionEnum extends IProcessId, GenContext extends IProcessContext, GenProcess extends IProcess> {

    protected IPreconditionFactory<GenPreconditionEnum> preconditionFactory;

    protected ICheckFactory<GenCheckEnum> checkFactory;

    protected IActionFactory<GenActionEnum> actionFactory;

	/**
	 * Muss im jeweiligen Service implementiert werden. Für die Sendung findet hier die Hierarchie Auswertung
	 * und der Abgleich mit dem im Auftrag definierten Soll und Ist Werten zu Sendungen statt.
	 * 
	 * @param pContext der Context, der als Container Daten und Konfiguration fuer den Pozesss speichert und bereithaelt
	 * @return Liste der Prozesse die ausgewertet werden muessen
	 */
	protected abstract List<GenProcess> getSubProcessList(GenContext pContext);

    public abstract void setProtocolService(IProtocolService protocolService);

	/**
	 * Diese Service Methode implementiert die Logik fuer die Durchfuehrung von komplexen Geschaeftsfunktionen wie z.B. Sendungen,
	 * inklusive der Erkennung aller noetigen Teilsendungen.
	 * 
	 * Algorithmus
	 * 1. Ermitteln der durchzuführenden Teilprozesse (z.B. Teilsendungen)
	 * 2. Ermitteln aller Pruefungen
	 *      2.1 Ermittlen der Pruefungs-IDs
	 *      2.2 Ermitteln der Pruefungs-Objekte
	 *      2.3 Ermitteln aller Pruefungs-Vorbedingungen-IDs
	 *      2.4 Durchfuehren aller Vorbedingungen für Prüfungen (Fortschreiben des Protokolls)
	 * 3. Durchfuehrung aller Pruefungen (Fortschreiben eines Protokolls) <Prüfungen erfolgreich dann weiter bei 5.>
	 * 4. Rueckmeldung zum Client offener Rückfragen an den Benutzer <Uebergabe Parameter Protocol ist bereits ergaenzt>
	 *    Rueckmeldung sofort, falls fataler Fehler aufgetreten ODER unbestaetigte WARNUNG vorliegt
	 * 5. Ermittlung aller Aktionen (?? Reihenfolge)
	 * 6. Durchfuehren aller Aktionen (Fortschreiben des Protokolls) <Fehler: dann weiter bei 8.>
	 * 7. Ergebnis-Meldung <return>
	 * 
	 * Der Ergebnisparameter ist <code>true</code>, falls der Prozess (z.B. Sendung) korrekt durchgefuehrt wurde - sind Fehler oder vom Benutzer
	 * zu bestaetigende Warnungen aufgetreten, so wird <code>false</code> geliefert.
	 * 
	 * Der Parameter vom Typ <code>IProcessContext</code> muss initialisiert uebergeben werden und ein bereits initialisiertes
	 * Protocol enthalten. Dieser Protocol Container wird bei der Durchfuehrung der Pruefungen und Aktionen mit den auftretenden
	 * Meldungen fortgeschrieben.
	 * 
	 * Diese Meldungen sollen dann im Dialog dem Benutzer zur Kenntisnahme angezeigt werden.
	 * 
	 * Hat der Benutzer die bei diesem Prozess aufgetretenen Warnungen (diese koennen vom Benutzer uebergangen werden)
	 * "bestaetigt", so sind diese beim Folgeaufruf im Protocol (im Context) im Uebergabeparameter als bereits <b>acknowledged</b>
	 * hinterlegt.
	 * 
	 * @param pContext - der initialisierte ProzessContext mit einem bereits angelegten Protocol.
	 * @return false wenn der Benutzer weitere Bestaetigungen machen muss bzw. der erfolgreiche Prozessabschluss auf Grund von Fehlern nicht moeglich ist - true sonst
	 */
	public boolean execute(GenContext pContext) {
		Assert.isTrue(pContext != null,
		"Der Prozesscontext muss zum Start des Prozesses bereits angelegt sein - ist aber null.");
        Protocol protocol = pContext.getProtocol();
		Assert.isTrue(protocol != null,
		"Das Protocol des Prozesscontexts muss zum Start des Prozesses bereits angelegt sein - ist aber null.");

		///////////////////////////////////////////////////////////////////////////////
		// 1. Ermitteln der durchzufuehrenden Teilprozesse (z.B. alle Teilsendungen)
		List<GenProcess> subProcessList = getSubProcessList(pContext);

		///////////////////////////////////////////////////////////////////////////////
		// 2. Ermitteln aller Prüfungen
		//      2.1 - Einsammeln aller Pruefungs-IDs
		Set<GenCheckEnum> allCheckEnums = collectCheckEntryEnums(subProcessList);

		//      2.2 Ermitteln der Pruefungs-Objekte
		List<ICheck> allCheckList = createChecksForEnums(allCheckEnums);

		//      2.3 Ermitteln aller Pruefungs-Vorbedingungen-IDs
		Set<GenPreconditionEnum> allCheckPreconditionEnums = collectPreconditionEnums(allCheckList);

		Set<IPrecondition> allCheckPreconditionSet = createPreconditionsForEnums(allCheckPreconditionEnums);

		//      2.4 Durchfuehren aller Vorbedingungen für Prüfungen (Fortschreiben des Protokolls)
		processPreconditions(allCheckPreconditionSet, pContext);
		if (pContext.getProtocol().existsErrorOrWorse()) {
			return false;
		}

		///////////////////////////////////////////////////////////////////////////////
		// 3. Durchfuehrung aller Pruefungen (Fortschreiben eines Protokolls)
		processChecks(allCheckList, pContext);
		if (pContext.getProtocol().existsRelevantEntryEvenOrWorse(SeverityEnum.WARNING)) {
			///////////////////////////////////////////////////////////////////////////////
			// 4. Rueckmeldung zum Client offener Rückfragen an den Benutzer <Uebergabe Parameter Protocol ist bereits ergaenzt>
			//    Rueckmeldung sofort, falls fataler Fehler aufgetreten ODER unbestaetigte WARNUNG vorliegt
			return false;
		}

        // remove acknowleged protocol entries
        pContext.getProtocol().finallyRemoveAcknowledgedEntries();

		///////////////////////////////////////////////////////////////////////////////
		// 5. Ermittlung aller Aktionen
		//      5.1 - Einsammeln aller Aktionen-IDs
		Set<GenActionEnum> allActionEnums = collectActionEnums(subProcessList);

		//      5.2 Ermitteln der Aktions-Objekte
		List<IAction> allActionList = createActionsForEnums(allActionEnums);

		//      5.3 Ermitteln aller Pruefungs-Vorbedingungen-IDs
		Set<GenPreconditionEnum> allActionPreconditionEnums = collectPreconditionEnums(allActionList);

		Set<IPrecondition> allActionPreconditionSet = createPreconditionsForEnums(allActionPreconditionEnums);

		//      5.4 Durchfuehren aller Vorbedingungen für Aktionen (Fortschreiben des Protokolls)
		processPreconditions(allActionPreconditionSet, pContext);
		if (pContext.getProtocol().existsErrorOrWorse()) {
			return false;
		}

		///////////////////////////////////////////////////////////////////////////////
		// 6. Durchfuehren aller Aktionen (Fortschreiben des Protokolls)
		// (Reihenfolge ist WICHTIG)
		processActions(allActionList, pContext);

		///////////////////////////////////////////////////////////////////////////////
		// 7. Ergebnis-Meldung <return>
		return true;
	}

	protected Set<IPrecondition> createPreconditionsForEnums(
			Set<GenPreconditionEnum> pAllPreconditionEnums) {
		Set<IPrecondition> resultSet = new HashSet<IPrecondition>();
		for (GenPreconditionEnum preconditionEnum : pAllPreconditionEnums) {
			IPrecondition precondition = preconditionFactory
			.createPrecondition(preconditionEnum);
			resultSet.add(precondition);
		}
		return resultSet;
	}

	protected List<ICheck> createChecksForEnums(Set<GenCheckEnum> pAllCheckEntries) {
		List<ICheck> resultList = new ArrayList<ICheck>();
		for (GenCheckEnum checkIdentifierEnum : pAllCheckEntries) {
			ICheck createCheck = checkFactory.createCheck(checkIdentifierEnum);
			resultList.add(createCheck);
		}
		return resultList;
	}

	protected List<IAction> createActionsForEnums(Set<GenActionEnum> pAllActionEnums) {
		List<IAction> resultList = new ArrayList<IAction>();
		for (GenActionEnum actionEnum : pAllActionEnums) {
			IAction createAction = actionFactory.createAction(actionEnum);
			resultList.add(createAction);
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	protected Set<GenActionEnum> collectActionEnums(List<GenProcess> pProcessList) {
        Set<GenActionEnum> actionSet = new LinkedHashSet<GenActionEnum>();
		for (GenProcess iProcess : pProcessList) {
			actionSet.addAll(iProcess.getActions());
		}
		return actionSet;
	}

	@SuppressWarnings("unchecked")
	protected Set<GenCheckEnum> collectCheckEntryEnums(List<GenProcess> pProcessList) {
        Set<GenCheckEnum> checkSet = new LinkedHashSet<GenCheckEnum>();
		for (GenProcess iProcess : pProcessList) {
			checkSet.addAll(iProcess.getChecks());
		}
		return checkSet;
	}

	@SuppressWarnings({ "unchecked" })
	protected Set<GenPreconditionEnum> collectPreconditionEnums(List<? extends IProcessStep> processStepSet) {
        Set<GenPreconditionEnum> resultSet = new LinkedHashSet<GenPreconditionEnum>();
		for (IProcessStep iProcessStep : processStepSet) {
			List<GenPreconditionEnum> singlePreconditionList = iProcessStep.getPreconditionIdentifier();
			resultSet.addAll(singlePreconditionList);
		}
		return resultSet;
	}

	/**
	 * Wertet alle vorhandenen Preconditions aus und legt die erforderlichen Daten im <code>Context</code> ab.
	 * 
	 * Tritt bei der Datenvorbereitung in Fehler auf, so wird ein entsprechender <code>ProtocolEntry</code> im
	 * <code>Protocol</code>Protocol des <code>Context</code> angelegt und als Ergebnis <code>false</code>
	 * zurueckgeliefert.
	 * 
	 * Tritt kein Fehler auf wird <code>true</code> zurueckgeliefert.
	 */
	protected void processPreconditions(Set<IPrecondition> pAllPreconditionSet,
			GenContext pContext) {
		List<IPrecondition> orderedList = new ArrayList<IPrecondition>(pAllPreconditionSet);
		Collections.sort(orderedList, new ProcessPreconditionComparator<IPrecondition>());
        for (IPrecondition iPrecondition : orderedList) {
			iPrecondition.doPrepareContext(pContext);
		}
	}

	/**
	 * Fuehrt alle Checks aus. Verwendet dazu die Daten aus dem Context.
	 * Der Context wurde vorher durch die CheckPreconditions gefuellt.
	 */
	protected void processChecks(List<ICheck> checkList, GenContext pContext) {
        SendContext ctx = (SendContext)pContext;
		for (ICheck iCheck : checkList) {
            iCheck.doExecute(pContext);
            if (pContext.getProtocol().existsErrorOrWorse()) {
                break;
            }
		}
	}

    /**
     * Führt die Aktionen durch und schreibt das Protokoll fort
     * 
     * @param pActionList durchzuführende Aktionen
     * @param pContext enthaelt auch Protocol Handle für die Fortschreibung
     * @return true wenn keine Fehler- oder Fatal- Eintraege geschrieben wurden, ansonsten false
     */
	protected void processActions(List<IAction> pActionList, GenContext pContext) {
        if (BasisObjectUtil.isEmptyOrNull(pActionList)) {
            return;
        }
		Collections.sort(pActionList, new ProcessActionComparator());
		for (IAction iAction : pActionList) {
			iAction.doExecute(pContext);
		}
	}

	public void setPreconditionFactory(IPreconditionFactory<GenPreconditionEnum> pPreconditionFactory) {
		preconditionFactory = pPreconditionFactory;
	}

	public void setCheckFactory(ICheckFactory<GenCheckEnum> pCheckFactory) {
		checkFactory = pCheckFactory;
	}

	public void setActionFactory(IActionFactory<GenActionEnum> pActionFactory) {
		actionFactory = pActionFactory;
	}

	protected IPreconditionFactory<GenPreconditionEnum> getPreconditionFactory() {
		return preconditionFactory;
	}

	protected ICheckFactory<GenCheckEnum> getCheckFactory() {
		return checkFactory;
	}

	protected IActionFactory<GenActionEnum> getActionFactory() {
		return actionFactory;
	}
}