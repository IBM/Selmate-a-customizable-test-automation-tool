
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class ClickCommand extends AbstractElementInteractionCommand {

	private Logger logger = Logger.getLogger(ClickCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		findElement(driver).click();
		logger.info("END");
		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.CLICK.name();
	}

}
