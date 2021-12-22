
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class CloseCommand extends AbstractCommand {

	private Logger logger = LogManager.getLogger(CloseCommand.class);

	@Override
	public String getName() {
		return CommandTypes.CLOSE.name();
	}

	@Override
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		Window window = selmateContext.getCurrentWindow();
		if (!window.isClosed()) {
			logger.info("Closing the current Window.");
			window.setClosed(true);
			driver.close();
		} else {
			logger.info("Current Window is already closed.");
		}
		return true;
	}

}
