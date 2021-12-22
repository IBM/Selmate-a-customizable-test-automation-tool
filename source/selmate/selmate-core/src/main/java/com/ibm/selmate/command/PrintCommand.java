
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.report.ReportManager;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public class PrintCommand extends AbstractCommand {

	private String value;

	private Logger logger = LogManager.getLogger(PrintCommand.class);

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return CommandTypes.PRINT.name();
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		validateNullorBlank(errorHandler, "VALUE", value, selmateContext);
	}

	@Override
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		System.out.println("PrintCommand OUTPUT:");
		this.value = selmateContext.evaluateVariables(value);
		System.out.println(value);
		logger.info("PrintCommand OUTPUT:");
		logger.info(value);
		logger.info("END");

		return true;
	}

	@Override
	public void log(boolean status, String stepDescription, SelmateContext selmateContext)
			throws SelmateExecutionException {
		logger.info("START");
		ReportManager.getInstance().log(selmateContext.getCurrentStep(), CommandTypes.PRINT.name(), stepDescription,
				status ? "PASSED" : "FAILED", "  -  " + value);
		logger.info("ENDT");
	}
}
