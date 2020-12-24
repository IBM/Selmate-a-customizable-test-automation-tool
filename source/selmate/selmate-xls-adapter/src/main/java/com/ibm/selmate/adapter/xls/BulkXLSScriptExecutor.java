package com.ibm.selmate.adapter.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.util.ExecCommandParserUtil;

public class BulkXLSScriptExecutor {

	static final Logger logger = Logger.getLogger(XLSScriptExecutor.class.getName());

	public static void main(String[] args) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		logger.info("Execution started on " + timestamp);
		try {
			if (args.length < 2) {
				throw new SelmateException(
						"Usage: SelmateScriptExecutorClient --file {xls filePath} [--options {option1 option2 ...}]");
			}
			List<File> inputFiles = ExecCommandParserUtil.getScriptFiles(args);
			new BulkXLSScriptExecutor().scheduleExecutions(inputFiles);
			logger.info("Execution finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void scheduleExecutions(List<File> inputFiles) throws InterruptedException, ExecutionException {
		int len = inputFiles.size();
		List<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		ExecutorService executorService = Executors.newFixedThreadPool(len);
		try {
			for (File inputFile : inputFiles) {
				AsyncXLSScriptExecutor asyncXLSScriptExecutor = new AsyncXLSScriptExecutor(inputFile);
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

	private class AsyncXLSScriptExecutor implements Callable<Boolean> {

		private File inputFile;

		private AsyncXLSScriptExecutor(File inputFile) {
			this.inputFile = inputFile;
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

}
