
package com.ibm.selmate.adapter.xls;

import java.util.List;

import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;

public interface SelmateScriptGenerator {

	public String generate(List<ScriptCommand> scriptCommands) throws SelmateXLSAdapterException;

}
