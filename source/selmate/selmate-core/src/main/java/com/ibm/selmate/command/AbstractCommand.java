
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.report.ReportManager;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.SelmateConstants;

public abstract class AbstractCommand implements Command {

	private Logger logger = LogManager.getLogger(AbstractCommand.class);

	private String stepDescription;

	public String getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("This is a blank implementation of command validation.");
	}

	protected void validateNullorBlank(CommandValidationErrorHandler errorHandler, String name, String value,
			SelmateContext selmateContext) {
		if (null == value || value.equalsIgnoreCase("")) {
			errorHandler.addErrorMessage(selmateContext.getCurrentStep(), name,
					SelmateConstants.NULL_OR_BLANK_ERROR_MSG);
		}
	}

	protected void validatePositiveInteger(CommandValidationErrorHandler errorHandler, String name, long value,
			SelmateContext selmateContext) {
		if (value < 0) {
			errorHandler.addErrorMessage(selmateContext.getCurrentStep(), name,
					SelmateConstants.NEGATIVE_INTEGER_ERROR_MSG);
		}
	}

	public void log(boolean status, String stepDescription, SelmateContext selmateContext)
			throws SelmateExecutionException {
		ReportManager.getInstance().log(selmateContext.getCurrentStep(), getName(), stepDescription,
				status ? "PASSED" : "FAILED");
	}

}
