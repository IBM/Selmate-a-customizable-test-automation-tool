
package com.ibm.selmate;

import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.util.DateFormatter;

/**
 * This class represents a factory for {@link SelmateScriptExecutor}.
 * 
 * @author Avijit Basak
 * 
 */
public class SelmateScriptExecutorFactory {

	private static final SelmateScriptExecutorFactory instance = new SelmateScriptExecutorFactory();

	private SelmateScriptExecutorFactory() {

	}

	/**
	 * This operation sets the script name to current {@link SelmateContext} and
	 * returns an instance of {@link SelmateScriptExecutor}.
	 * 
	 * @param scriptName
	 * @return {@link SelmateScriptExecutor}
	 * @throws SelmateException
	 */
	public SelmateScriptExecutor create(String scriptName) throws SelmateException {
		if (scriptName == null || scriptName.trim().equals("")) {
			scriptName = "SELMATE-SCRIPT " + DateFormatter.format();
		}
		SelmateContextImpl.getCurrentContext().setScriptName(scriptName);
		return new SelmateScriptExecutorImpl();
	}

	public static SelmateScriptExecutorFactory getInstance() {
		return instance;
	}

}
