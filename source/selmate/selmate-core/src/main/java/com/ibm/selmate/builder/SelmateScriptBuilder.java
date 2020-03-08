
package com.ibm.selmate.builder;

import com.ibm.selmate.command.SelmateScript;
import com.ibm.selmate.exception.SelmateExecutionException;

public interface SelmateScriptBuilder {

	public SelmateScript build(String xmlString) throws SelmateExecutionException;

}
