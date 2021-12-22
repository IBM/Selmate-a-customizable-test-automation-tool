
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class DelayCommand extends AbstractWaitCommand {

	private Logger logger = LogManager.getLogger(DelayCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		long value = this.getTimeInterval();
		logger.info("delay Time :" + value);
		try {
			Thread.sleep(value);
		} catch (InterruptedException ex) {
			throw new SelmateExecutionException(ex);
		}
		logger.info("END");

		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.DELAY.name();
	}

}
