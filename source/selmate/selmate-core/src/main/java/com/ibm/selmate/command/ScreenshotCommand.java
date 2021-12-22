
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.report.ReportManager;
import com.ibm.selmate.util.CommandTypes;

public class ScreenshotCommand extends AbstractCommand {

	private byte[] imgData;

	private Logger logger = LogManager.getLogger(ScreenshotCommand.class);

	@Override
	public String getName() {
		return CommandTypes.SCREENSHOT.name();
	}

	@Override
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		if (TakesScreenshot.class.isAssignableFrom(driver.getClass())) {
			imgData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			logger.info("END");
			return true;
		} else {
			logger.fatal("Driver class is not an instance of TakesScreenshot class.");
			logger.info("END");
			return false;
		}
	}

	@Override
	public void log(boolean status, String stepDescription, SelmateContext selmateContext)
			throws SelmateExecutionException {
		ReportManager.getInstance().log(selmateContext.getCurrentStep(), getName(), getStepDescription(), "PASSED",
				imgData);
	}

}
