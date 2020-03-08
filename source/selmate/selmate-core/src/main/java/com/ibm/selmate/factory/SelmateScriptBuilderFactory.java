
package com.ibm.selmate.factory;

import com.ibm.selmate.builder.SelmateScriptBuilder;
import com.ibm.selmate.builder.SelmateScriptBuilderImpl;

/**
 * This class represents factory for Selmate Script builder.
 * 
 * @author Avijit Basak
 * 
 */
public class SelmateScriptBuilderFactory {

	private static SelmateScriptBuilderFactory factory = null;

	private SelmateScriptBuilderFactory() {
	}

	public static SelmateScriptBuilderFactory getInstance() {

		if (factory == null) {
			synchronized (SelmateScriptBuilderFactory.class) {
				if (factory == null) {
					factory = new SelmateScriptBuilderFactory();
				}
			}
		}

		return factory;
	}

	/**
	 * This operation returns the default {@link SelmateScriptBuilder}.
	 * 
	 * @return {@link SelmateScriptBuilder}
	 */
	public SelmateScriptBuilder create() {
		return new SelmateScriptBuilderImpl();
	}

}
