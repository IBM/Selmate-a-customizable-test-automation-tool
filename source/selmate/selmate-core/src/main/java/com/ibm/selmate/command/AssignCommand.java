
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.SelmateConstants;

public class AssignCommand extends AbstractCommand {

	private Logger logger = LogManager.getLogger(AssignCommand.class);

	private String value;

	private String variableName;

	public void setValue(String value) {
		this.value = value;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		logger.info("Assign Command:" + value + "  " + variableName);
		validateNullorBlank(errorHandler, "VALUE", value, selmateContext);
		validateNullorBlank(errorHandler, "VARIABLE NAME", variableName, selmateContext);
		validateVariableName(errorHandler, selmateContext, variableName);
		logger.info("END");
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");

		selmateContext.storeVariable(variableName, selmateContext.evaluateVariables(value));
		logger.info("END");

		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.ASSIGN.name();
	}

	private void validateVariableName(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext,
			String variableName) {
		if (!SelmateConstants.VARIABLE_NAME_PATTERN.matcher(variableName).matches()) {
			errorHandler.addErrorMessage(selmateContext.getCurrentStep(), variableName,
					SelmateConstants.INVALID_VARIABLE_NAME_ERROR_MSG + SelmateConstants.VARIABLE_NAME_PATTERN_STR);
		}
	}

}
