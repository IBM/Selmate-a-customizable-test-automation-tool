
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class SwitchToFrameCommand extends AbstractCommand {

	private String id;

	private int index = -1;

	private Logger logger = Logger.getLogger(SwitchToFrameCommand.class);

	public void setId(String id) {
		this.id = id;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		if (index > -1) {
			driver.switchTo().frame(index);
		} else {
			driver.switchTo().frame(id);
		}
		logger.info("END");
		return true;
	}

	@Override
	public String getName() {
		return CommandTypes.SWITCH_FRAME.name();
	}

}
