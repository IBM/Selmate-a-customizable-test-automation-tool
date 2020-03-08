/*

 */

package com.ibm.selmate;

import com.ibm.selmate.command.Window;
import com.ibm.selmate.exception.SelmateExecutionException;

/**
 * This interface represents the current execution Context for the of Selmate.
 * 
 * @author Avijit Basak
 * 
 */
public interface SelmateContext {

	public String getScriptName();

	public long getImplicitWaitTime();

	public long getPollingInterval();

	public int getCurrentStep();

	public Object getVariableValue(String name);

	public boolean isVariableNameReference(String name);

	public void storeVariable(String name, Object value);

	public Object containsVariable(String name);

	public Object removeVariable(String name);

	public void setImplicitWaitTime(long delayTime);

	public Window getMainWindow();

	public Window getCurrentWindow();

	public boolean isExistingWindowHandle(String handle);

	public String evaluateVariables(String content) throws SelmateExecutionException;

}