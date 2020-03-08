
package com.ibm.selmate;

import java.io.InputStream;
import java.io.Reader;

import com.ibm.selmate.exception.SelmateExecutionException;

/**
 * This interface represents a Selmate script executor in XML format.
 * 
 * @author Avijit Basak
 * 
 */
public interface SelmateScriptExecutor {

	/**
	 * This operation is responsible for reading XML format of Selmate script,
	 * validates the same against the schema and then executes it.
	 * 
	 * @param scriptReader
	 * @throws SelmateExecutionException
	 */
	public void execute(Reader scriptReader) throws SelmateExecutionException;

	/**
	 * This operation is responsible for reading XML format of Selmate script,
	 * validates the same against the schema and then executes it.
	 * 
	 * @param inputStream
	 * @throws SelmateExecutionException
	 */
	public void execute(InputStream inputStream) throws SelmateExecutionException;

	/**
	 * This operation is responsible for reading XML format of Selmate script,
	 * validates the same against the schema and then executes it.
	 * 
	 * @param script
	 * @throws SelmateExecutionException
	 */
	public void execute(String script) throws SelmateExecutionException;

}
