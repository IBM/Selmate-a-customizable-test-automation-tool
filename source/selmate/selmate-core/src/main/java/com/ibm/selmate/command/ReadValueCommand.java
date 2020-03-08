
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;

public class ReadValueCommand extends AbstractReadCommand {

	private String attributeName;

	private Logger logger = Logger.getLogger(ReadValueCommand.class);

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		String value = null;
		Locator locator = getElement().getLocator();
		WebElement element = findElement(driver);
		if (locator.getType() == Locator.Type.XPATH) {
			value = element.getText();
		} else {
			value = element.getAttribute(attributeName);
		}
		selmateContext.storeVariable(this.getVariableName(), value);
		logger.info("END");

		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.READVALUE.name();
	}
}
