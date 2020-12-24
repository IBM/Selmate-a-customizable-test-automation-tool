
package com.ibm.selmate;

import com.ibm.selmate.command.Window;
import com.ibm.selmate.exception.SelmateExecutionException;

/**
 * This class represents an adapter to the Selmate context and implements
 * {@link SelmateContext}.
 * 
 * @author Avijit Basak
 * 
 */
public final class SelmateContextAdapter implements SelmateContext {

	private static ThreadLocal<SelmateContextAdapter> instance = new ThreadLocal<>();

	private SelmateContextImpl context;

	private SelmateContextAdapter() {
		this.context = SelmateContextImpl.getCurrentContext();
	}

	public String getScriptName() {
		return this.context.getScriptName();
	}

	public long getImplicitWaitTime() {
		return this.context.getImplicitWaitTime();
	}

	public long getPollingInterval() {
		return this.context.getPollingInterval();
	}

	public Object getVariableValue(String name) {
		return this.context.getVariableValue(name);
	}

	public boolean isVariableNameReference(String name) {
		return this.context.isVariableNameReference(name);
	}

	@Override
	public void storeVariable(String name, Object value) {
		this.context.storeVariable(name, value);
	}

	@Override
	public Object removeVariable(String name) {
		return this.context.removeVariable(name);
	}

	@Override
	public Object containsVariable(String name) {
		return this.context.containsVariable(name);
	}

	@Override
	public int getCurrentStep() {
		return this.context.getCurrentStep();
	}

	@Override
	public void setImplicitWaitTime(long delayTime) {
		this.context.setImplicitWaitTime(delayTime);
	}

	@Override
	public Window getMainWindow() {
		return this.context.getMainWindow();
	}

	@Override
	public Window getCurrentWindow() {
		return this.context.getCurrentWindow();
	}

	@Override
	public boolean isExistingWindowHandle(String handle) {
		return this.context.isExistingWindowHandle(handle);
	}

	@Override
	public String evaluateVariables(String content) throws SelmateExecutionException {
		return this.context.evaluateVariables(content);
	}

	/**
	 * This operation returns an instance of this class.
	 * 
	 * @return {@link SelmateContext}
	 */
	public static final SelmateContext getCurrentContext() {
		if (instance.get() == null) {
			instance.set(new SelmateContextAdapter());
		}
		return instance.get();
	}

}
