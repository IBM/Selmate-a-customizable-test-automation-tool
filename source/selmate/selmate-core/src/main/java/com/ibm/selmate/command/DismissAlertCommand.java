
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class DismissAlertCommand extends AbstractAlertCommand {

	private Logger logger = LogManager.getLogger(DismissAlertCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		try {
			logger.info("START");
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			logger.info("END");
			return true;
		} catch (AWTException e) {
			throw new SelmateExecutionException(e);
		} catch (NoAlertPresentException e) {
			throw new SelmateExecutionException("No alert present to dismiss", e);
		}

	}

	@Override
	public String getName() {
		return CommandTypes.DISMISS_ALERT.name();
	}

}
