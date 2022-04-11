package com.daimler.emst2.fhi.sendung.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.daimler.emst2.fhi.model.IProcessId;
import com.daimler.emst2.fhi.model.ITuebObject;


/**
 * Abstract Sendung implementiert alle rekursiven Methoden von @see ISend. Damit muss eine Realisierung einer (Teil-)Sendung nur
 * die Methoden für die eigenen Pruefungen und Aktionen überschreiben.
 * 
 * @author thb
 */
public class Process<GenTypeEnum extends ITuebObject, GenActionEnum extends IProcessId, GenCheckEnum extends IProcessId>
implements IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum> {

    private final IProcessConfiguration<GenTypeEnum, GenCheckEnum, GenActionEnum> processConfiguration;

    private final List<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>> children = new ArrayList<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>>();

    private final List<GenActionEnum> actionTypeList = new ArrayList<GenActionEnum>();

    private final List<GenCheckEnum> checkTypeList = new ArrayList<GenCheckEnum>();


//    public static final <S extends IProcess<TypeEnum, CheckEnum, ActionEnum>> S createProcess(IProcessConfiguration<TypeEnum, CheckEnum, ActionEnum>) {
//    	return null;
//    }
    
    public Process(IProcessConfiguration<GenTypeEnum, GenCheckEnum, GenActionEnum> pProcessConfiguration) {
        super();
        processConfiguration = pProcessConfiguration;
        init();
    }

    /**
     * Hier wird in der konkreten Sendungsimplementierung die Liste der Actions und Checks befuellt.
     */
    protected void init() {
        processConfiguration.fillCheckEnumList(getChecks());
        processConfiguration.fillActionEnumList(getActions());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenTypeEnum getType() {
        return processConfiguration.getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GenActionEnum> getActions() {
        return actionTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GenCheckEnum> getChecks() {
        return checkTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <A extends IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>> void addChild(A pChild) {
        Assert.notNull(pChild, "Es darf kein NULL Eintrag in die Hirarchie eingefügt werden");
        children.add(pChild);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>> getChildren() {
        return children;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>> getSubtreeFlat() {
        List<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>> result = new ArrayList<IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum>>();
        result.add(this);
        if (isLeaf()) {
            return result;
        }
        for (IProcess<GenTypeEnum, GenCheckEnum, GenActionEnum> child : children) {
            result.addAll(child.getSubtreeFlat());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }
}
