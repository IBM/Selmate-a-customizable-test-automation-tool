
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.SelmateConstants;

public class DropCommand extends AbstractInputCommand {

	private Logger logger = Logger.getLogger(DropCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		WebElement dropTo = findElement(driver);
		WebElement dragFrom = (WebElement) selmateContext.getVariableValue(SelmateConstants.DRAG_DROP_VARIABLE_NAME);
		Actions action = new Actions(driver);
		action.dragAndDrop(dragFrom, dropTo).perform();
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.DROP_TO.name();
	}
}
