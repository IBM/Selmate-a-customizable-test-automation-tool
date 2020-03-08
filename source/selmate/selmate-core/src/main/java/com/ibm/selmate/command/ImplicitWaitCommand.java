
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class ImplicitWaitCommand extends AbstractWaitCommand {

	private Logger logger = Logger.getLogger(ImplicitWaitCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		Unit unit = this.getUnit();
		long value = this.getTimeInterval();
		long timeInSec = -1;
		if (unit == Unit.S) {
			timeInSec = value;
		} else if (unit == Unit.M) {
			timeInSec = value * 60;
		} else if (unit == Unit.H) {
			timeInSec = value * 3600;
		}
		selmateContext.setImplicitWaitTime(timeInSec);
		logger.info("END");
		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.WAIT.name();
	}

}
