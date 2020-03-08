
package com.ibm.selmate;

import java.io.InputStream;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.builder.SelmateScriptBuilder;
import com.ibm.selmate.command.SelmateScript;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.exception.SelmateScriptParserException;
import com.ibm.selmate.exception.SelmateValidationException;
import com.ibm.selmate.factory.SelmateScriptBuilderFactory;
import com.ibm.selmate.util.ScriptReader;
import com.ibm.selmate.validator.SchemaValidator;

/**
 * This class represents an implementation of {@link SelmateScriptExecutor}. It
 * accepts a Selmate script in XML format, validates it and execute the same.
 * 
 * @author Avijit Basak
 * 
 */
final class SelmateScriptExecutorImpl implements SelmateScriptExecutor {

	static final Logger logger = Logger.getLogger("reportsLogger");

	@Override
	public void execute(Reader scriptReader) throws SelmateExecutionException {
		try {
			execute(ScriptReader.read(scriptReader));
		} catch (SelmateScriptParserException e) {
			throw new SelmateExecutionException(e);
		}
	}

	@Override
	public void execute(String script) throws SelmateExecutionException {
		try {
			logger.info("Input XML script:");
			logger.info(script);
			System.out.print("Validating script");
			SchemaValidator.getInstance().validate(script);
			SelmateScript selmateScript = buildScript(script);
			execute(selmateScript);
		} catch (SelmateValidationException e) {
			logger.fatal(script);
			throw new SelmateExecutionException(e);
		}

	}

	@Override
	public void execute(InputStream scriptInputStream) throws SelmateExecutionException {
		try {
			execute(ScriptReader.read(scriptInputStream));
		} catch (SelmateScriptParserException e) {
			throw new SelmateExecutionException(e);
		}
	}

	/**
	 * This operation accepts an instance of {@link SelmateScript}, validates the
	 * same and execute it.
	 * 
	 * @param selmateScript
	 * @throws SelmateExecutionException
	 * @throws SelmateValidationException
	 */
	private void execute(SelmateScript selmateScript) throws SelmateExecutionException, SelmateValidationException {
		try {
			logger.info("Start of execute method inside  SelmateScriptExecutorImpl");
			System.out.println("Validating script.");
			selmateScript.validate();
			System.out.println("Validation Complete.");
			System.out.println("Executing Script.");
			WebDriverManager webDriverManager = WebDriverManager.getInstance();
			WebDriver driver = webDriverManager.create();
			selmateScript.execute(driver);
			webDriverManager.close();
			System.out.println("Execution Finished.");
			logger.info("End of execute method inside  SelmateScriptExecutorImpl");
		} catch (Exception ex) {
			throw new SelmateExecutionException(ex);
		}
	}

	/**
	 * This operation creates an instance of {@link SelmateScript} from script
	 * string in XML format.
	 * 
	 * @param scriptStr
	 * @return {@link SelmateScript}
	 * @throws SelmateExecutionException
	 */
	private SelmateScript buildScript(String scriptStr) throws SelmateExecutionException {
		logger.info("START");
		SelmateScriptBuilderFactory selmateScriptBuilderFactory = SelmateScriptBuilderFactory.getInstance();
		SelmateScriptBuilder scriptBuilder = selmateScriptBuilderFactory.create();
		SelmateScript script = scriptBuilder.build(scriptStr);
		logger.info("END");
		return script;
	}

}
