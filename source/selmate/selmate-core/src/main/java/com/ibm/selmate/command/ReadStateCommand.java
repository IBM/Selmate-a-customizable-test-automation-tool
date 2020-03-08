
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class ReadStateCommand extends AbstractReadCommand {

	private State state;

	private Logger logger = Logger.getLogger(ReadStateCommand.class);

	public static enum State {
		DISPLAYED, ENABLED, SELECTED
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		WebElement webElement = findElement(driver);
		Boolean stateValue = false;

		if (this.state == State.DISPLAYED) {
			if (webElement.isDisplayed()) {
				stateValue = true;
			}
		} else if (this.state == State.ENABLED) {
			if (webElement.isEnabled()) {
				stateValue = true;
			}
		} else if (this.state == State.SELECTED) {
			if (webElement.isSelected()) {
				stateValue = true;
			}
		}

		selmateContext.storeVariable(getVariableName(), stateValue);
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.READSTATE.name();
	}

}
