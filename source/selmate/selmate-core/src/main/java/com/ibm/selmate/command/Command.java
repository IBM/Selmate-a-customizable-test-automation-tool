
package com.ibm.selmate.command;

import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public interface Command {

	public String getName();

	public String getStepDescription();

	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException;

	public void log(boolean status, String stepDescription, SelmateContext selmateContext)
			throws SelmateExecutionException;

}
