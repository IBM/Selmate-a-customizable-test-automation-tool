
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.SelmateConstants;

public class OpenCommand extends AbstractCommand {

	private String url;

	private Logger logger = LogManager.getLogger(OpenCommand.class);

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {

		logger.info("START");
		validateNullorBlank(errorHandler, "URL", url, selmateContext);
		if (url.startsWith("http")) {
			UrlValidator urlValidator = new UrlValidator();

			if (!urlValidator.isValid(this.url)) {
				errorHandler.addErrorMessage(selmateContext.getCurrentStep(), "URL", SelmateConstants.URL_ERROR_MSG);
			}
		} else if (url.startsWith("file")) {
			try {
				new URI(url);
			} catch (URISyntaxException e) {
				errorHandler.addErrorMessage(selmateContext.getCurrentStep(), "URL", SelmateConstants.URL_ERROR_MSG);
			}
		}

		logger.info("END");
	}

	/**
	 * This operation opens a web page in browser as per the mentioned URL.
	 */
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		driver.get(selmateContext.evaluateVariables(url));
		selmateContext.getMainWindow().setHandle(driver.getWindowHandle());
		logger.info("END");

		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.OPEN.name();
	}

}
