
package com.ibm.selmate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.ibm.selmate.command.Window;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.SelmateConstants;

/**
 * This class represents an implementation of current execution context of
 * Selmate and implements {@link SelmateContext}.
 * 
 * @author Avijit Basak
 * 
 */
final class SelmateContextImpl implements SelmateContext {

	private static ThreadLocal<SelmateContextImpl> instance = new ThreadLocal<SelmateContextImpl>() {
		protected SelmateContextImpl initialValue() {
			return new SelmateContextImpl();
		}
	};

	private String scriptName;

	// Implicit DELAY time in SECONDs.
	private long implicitWaitTime = 120;

	// Implicit polling interval in milliseconds.
	private final long pollingInterval = 500;

	public static final String VARIABLE_NAME_PREFIX = "$";

	private Map<String, Object> variables = new HashMap<String, Object>();

	private int currentStep;

	private Window mainWindow = new Window();

	private SelmateContextImpl() {

	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public long getImplicitWaitTime() {
		return implicitWaitTime;
	}

	public long getPollingInterval() {
		return pollingInterval;
	}

	public void setImplicitWaitTime(long delayTime) {
		this.implicitWaitTime = delayTime;
	}

	public void storeVariable(String name, Object value) {
		variables.put(name, value);
	}

	public Object getVariableValue(String name) {
		return variables.get(name);
	}

	public Object removeVariable(String name) {
		return variables.remove(name);
	}

	/**
	 * This operation is responsible for evaluating input values w.r.t. the
	 * referred variable values.
	 */
	public String evaluateVariables(String content) throws SelmateExecutionException {
		Matcher matcher = null;
		int index = 0;
		String variableNameRef = null;
		String variableName = null;
		String variableValue = null;
		StringBuilder evaluatedContent = new StringBuilder(content);

		while ((matcher = SelmateConstants.VARIABLE_NAME_REF_PATTERN.matcher(evaluatedContent)).find(index)) {
			variableNameRef = matcher.group();
			index = evaluatedContent.indexOf(variableNameRef, index);

			/*
			 * Check whether the variable reference is escaped by '\'.
			 */
			if ((index == 1 && evaluatedContent.charAt(index - 1) == '\\') || (index >= 2
					&& evaluatedContent.charAt(index - 1) == '\\' && evaluatedContent.charAt(index - 2) != '\\')) {
				evaluatedContent = evaluatedContent.replace(index - 1, index, "");
				index = index + variableNameRef.length() - 1;
				continue;
			}
			variableName = getVariableNameFromReference(variableNameRef);

			/*
			 * Check whether the variable is defined or not.
			 */
			if (!isVariableDefined(variableName)) {
				throw new SelmateExecutionException("Variable " + variableName + " is not defined.");
			}
			variableValue = getVariableValue(variableName).toString();
			index = evaluatedContent.indexOf(variableNameRef, index);
			evaluatedContent = evaluatedContent.replace(index, index + variableNameRef.length(), variableValue);
			index = index + variableValue.length();
		}

		return evaluatedContent.toString();
	}

	/**
	 * This operation indicates whether the variable is defined or not.
	 * 
	 * @param variableName
	 * @return
	 */
	private boolean isVariableDefined(String variableName) {
		return variables.containsKey(variableName);
	}

	/**
	 * This operation returns name of the variable from referred variable name.
	 * 
	 * @param variableReference
	 * @return
	 */
	private String getVariableNameFromReference(String variableReference) {
		Matcher matcher = SelmateConstants.VARIABLE_NAME_PATTERN.matcher(variableReference);
		matcher.find();
		return matcher.group();
	}

	public boolean containsVariableReference(String content) {
		return SelmateConstants.VARIABLE_NAME_REF_PATTERN.matcher(content).find();
	}

	public boolean isVariableNameReference(String name) {
		return SelmateConstants.VARIABLE_NAME_REF_PATTERN.matcher(name).matches();
	}

	@Override
	public Object containsVariable(String name) {
		return variables.containsKey(name);
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public int incrementCurrentStep() {
		return ++currentStep;
	}

	public void resetStep() {
		this.currentStep = 0;
	}

	public Window getMainWindow() {
		return mainWindow;
	}

	public Window getCurrentWindow() {
		Window currentWindow = mainWindow;
		while (currentWindow.getChild() != null) {
			currentWindow = currentWindow.getChild();
		}
		return currentWindow;
	}

	@Override
	public boolean isExistingWindowHandle(String handle) {
		if (handle == null) {
			throw new NullPointerException("Windlw handle cannot be Null");
		}
		if (mainWindow.getHandle().equals(handle)) {
			return true;
		}
		Window currentWindow = mainWindow;
		while (currentWindow.getChild() != null) {
			currentWindow = currentWindow.getChild();
			if (currentWindow.getHandle().equals(handle)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This operation returns an instance of Selmate context implementation.
	 * 
	 * @return {@link SelmateContextImpl}
	 */
	public static SelmateContextImpl getCurrentContext() {
		return instance.get();
	}

}
