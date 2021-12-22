
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public abstract class AbstractReadCommand extends AbstractElementInteractionCommand {

	private String variableName;

	Logger logger = LogManager.getLogger(AbstractReadCommand.class);

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		super.validate(errorHandler, selmateContext);
		validateNullorBlank(errorHandler, "VARIABLE NAME", variableName, selmateContext);
		logger.info("END");
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

}
