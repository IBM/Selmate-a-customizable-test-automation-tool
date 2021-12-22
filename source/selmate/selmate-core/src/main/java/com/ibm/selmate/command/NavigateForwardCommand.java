
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class NavigateForwardCommand extends AbstractCommand {

	private Logger logger = LogManager.getLogger(NavigateForwardCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		driver.navigate().forward();
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.HIST_FWD.name();
	}
}
