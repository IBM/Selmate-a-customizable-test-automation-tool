
package com.ibm.selmate.adapter.xls;

import java.io.InputStream;
import java.util.List;

import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;

public interface ScriptReader {

	public List<ScriptCommand> read(InputStream inputStream) throws SelmateXLSAdapterException;

}
