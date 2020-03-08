
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class SelectCommand extends AbstractInputCommand {

	private boolean status;

	private Logger logger = Logger.getLogger(SelectCommand.class);

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		WebElement webElement = findElement(driver);
		if ((status && !webElement.isSelected()) || (!status && webElement.isSelected())) {
			webElement.click();
		}
		logger.info("END");

		return true;

	}

	@Override
	public String getName() {
		return status ? CommandTypes.SELECT.name() : CommandTypes.DESELECT.name();
	}

}
