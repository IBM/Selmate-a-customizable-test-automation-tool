
package com.ibm.selmate.adapter.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibm.selmate.SelmateScriptExecutor;
import com.ibm.selmate.SelmateScriptExecutorFactory;
import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.ExecCommandParserUtil;

public class XLSScriptExecutor {

	static final Logger logger = Logger.getLogger(XLSScriptExecutor.class);

	public static void main(String[] args) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		logger.info("Execution started on " + timestamp);
		try {
			if (args.length < 2) {
				throw new SelmateException(
						"Usage: SelmateScriptExecutorClient --file {xls filePath} [--options {option1 option2 ...}]");
			}

			List<File> inputFiles = ExecCommandParserUtil.getScriptFiles(args);
			for (File inputFile : inputFiles) {
				String scriptName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf("."));
				XLSScriptExecutor xlsScriptExecutor = new XLSScriptExecutor();
				xlsScriptExecutor.execute(scriptName, new FileInputStream(inputFile));
			}
			logger.info("Execution finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void execute(String scriptName, InputStream inputStream) {
		try {
			logger.info("[" + scriptName + "]" + "Start of execute method inside XLSScriptExecutor");
			System.out.println("[" + scriptName + "]" + "Executing XLS Script ...");
			ScriptReader scriptReader = new ScriptReaderImpl();
			List<ScriptCommand> scriptCommands = scriptReader.read(inputStream);
			SelmateScriptGenerator selmateScriptGenerator = new SelmateScriptGeneratorImpl();
			String selmateScript = selmateScriptGenerator.generate(scriptCommands);
			SelmateScriptExecutor selmateScriptExecutor = SelmateScriptExecutorFactory.getInstance().create(scriptName);
			selmateScriptExecutor.execute(selmateScript);
			logger.info("[" + scriptName + "]" + "End of execute method inside XLSScriptExecutor");
		} catch (SelmateXLSAdapterException e) {
			e.printStackTrace();
		} catch (SelmateExecutionException e) {
			e.printStackTrace();
		} catch (SelmateException e) {
			e.printStackTrace();
		}
	}

}