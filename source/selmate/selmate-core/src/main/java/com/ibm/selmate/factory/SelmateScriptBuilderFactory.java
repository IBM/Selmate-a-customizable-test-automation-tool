
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

	private static final SelmateScriptBuilderFactory factory = new SelmateScriptBuilderFactory();

	private SelmateScriptBuilderFactory() {
	}

	public static SelmateScriptBuilderFactory getInstance() {

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
