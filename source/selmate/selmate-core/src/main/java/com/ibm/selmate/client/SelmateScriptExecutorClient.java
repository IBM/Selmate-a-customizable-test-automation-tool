
package com.ibm.selmate.client;

import java.io.File;
import java.io.FileInputStream;

import com.ibm.selmate.SelmateScriptExecutor;
import com.ibm.selmate.SelmateScriptExecutorFactory;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.util.ExecCommandParserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is responsible to read xml file and execute the same as script.
 * 
 * @author Arup Dey
 * 
 */
public class SelmateScriptExecutorClient {

	private static Logger logger = LogManager.getLogger(SelmateScriptExecutorClient.class);

	public static void main(String[] args) {

		try {
			if (args.length < 2) {
				throw new SelmateException("Usage: SelmateScriptExecutorClient --file {filePath}");
			}
			File inputFile = ExecCommandParserUtil.getScriptFile(args);
			String scriptName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf("."));
			try (FileInputStream fileIS = new FileInputStream(inputFile);) {
				SelmateScriptExecutor scriptExecutor = SelmateScriptExecutorFactory.getInstance().create(scriptName);
				scriptExecutor.execute(fileIS);
			}
		} catch (Exception e) {
			logger.fatal("Exception", e);
		}

	}

}
