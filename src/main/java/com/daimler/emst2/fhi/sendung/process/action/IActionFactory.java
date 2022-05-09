package com.daimler.emst2.fhi.sendung.process.action;

import com.daimler.emst2.fhi.model.IProcessId;

/**
 * Framework Factory for Sendungen und Storno Actions unterschiedlichen Typs
 * 
 * @author thb
 *
 * @param <A> action type
 */
public interface IActionFactory<A extends IProcessId> {

	public IAction createAction(A pActionEnum);

}