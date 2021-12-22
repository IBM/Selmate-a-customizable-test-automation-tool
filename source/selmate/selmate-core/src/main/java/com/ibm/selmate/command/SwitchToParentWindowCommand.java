
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class SwitchToParentWindowCommand extends AbstractCommand {

	private Logger logger = LogManager.getLogger(SwitchToParentWindowCommand.class);

	@Override
	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		Window currentWindow = selmateContext.getCurrentWindow();
		if (currentWindow == selmateContext.getMainWindow()) {
			logger.fatal("Cannot move to parent window while the current window is MainWindow");
			throw new SelmateExecutionException("Cannot move to parent window while the current window is MainWindow");
		}
		Window parentWindow = currentWindow.getParent();
		parentWindow.setChild(null);
		driver.switchTo().window(parentWindow.getHandle());
		logger.info("END");

		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.SWITCH_PARENT_WINDOW.name();
	}

}
