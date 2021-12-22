
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class AcceptAlertCommand extends AbstractAlertCommand {

	private Logger logger = LogManager.getLogger(AcceptAlertCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		try {
			logger.info("START");
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.info("END");
			return true;
		} catch (NoAlertPresentException ex) {
			throw new SelmateExecutionException("No alert present to accept");
		}

	}

	@Override
	public String getName() {
		return CommandTypes.ACCEPT_ALERT.name();
	}

}
