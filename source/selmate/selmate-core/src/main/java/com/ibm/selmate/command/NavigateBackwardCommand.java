
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class NavigateBackwardCommand extends AbstractCommand {

	private Logger logger = LogManager.getLogger(NavigateBackwardCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		driver.navigate().back();
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.HIST_BKWD.name();
	}
}
