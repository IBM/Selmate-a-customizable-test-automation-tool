
package com.ibm.selmate.adapter.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.selmate.SelmateScriptExecutor;
import com.ibm.selmate.SelmateScriptExecutorFactory;
import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.ExecCommandParserUtil;

public class XLSScriptExecutor implements Callable<Boolean> {

	private File inputFile;

	static final Logger logger = LogManager.getLogger(XLSScriptExecutor.class);

	public static void main(String[] args) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		logger.info("Execution started on " + timestamp);
		try {
			if (args.length < 2) {
				throw new SelmateException(
						"Usage: SelmateScriptExecutorClient --file {xls filePath} [--options {option1 option2 ...}]");
			}
			if (ExecCommandParserUtil.isBulkExecution(args)) {
				List<File> inputFiles = ExecCommandParserUtil.getScriptFiles(args);
				if (inputFiles.size() == 0) {
					throw new SelmateException("Argument --dir not found.");
				}
				scheduleExecutions(inputFiles);
			} else {
				File inputFile = ExecCommandParserUtil.getScriptFile(args);
				XLSScriptExecutor xlsScriptExecutor = new XLSScriptExecutor();
				xlsScriptExecutor.inputFile = inputFile;
				xlsScriptExecutor.call();
			}
			logger.info("Execution finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void scheduleExecutions(List<File> inputFiles) throws InterruptedException, ExecutionException {
		int len = inputFiles.size();
		List<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		ExecutorService executorService = Executors.newFixedThreadPool(len);
		try {
			for (File inputFile : inputFiles) {
				XLSScriptExecutor asyncXLSScriptExecutor = new XLSScriptExecutor();
				asyncXLSScriptExecutor.inputFile = inputFile;
				Future<Boolean> result = executorService.submit(asyncXLSScriptExecutor);
				results.add(result);
			}
			for (Future<Boolean> result : results) {
				result.get();
			}
		} finally {
			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		}

	}

	private void execute(String scriptName, InputStream inputStream) {
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

	@Override
	public Boolean call() throws Exception {
		try (FileInputStream fis = new FileInputStream(inputFile)) {
			String scriptName = inputFile.getName().substring(0, inputFile.getName().lastIndexOf("."));
			new XLSScriptExecutor().execute(scriptName, fis);
			return true;
		} catch (FileNotFoundException e) {
			throw new SelmateException(e);
		}
	}

}