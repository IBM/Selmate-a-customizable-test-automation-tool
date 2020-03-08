
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class SwitchToDefultContentCommand extends AbstractCommand {

	private Logger logger = Logger.getLogger(SwitchToDefultContentCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		driver.switchTo().defaultContent();
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.SWITCH_TO_DEFAULT_CONTENT.name();
	}

}
