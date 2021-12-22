
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.SelmateConstants;

public class DragCommand extends AbstractInputCommand {

	private Logger logger = LogManager.getLogger(DragCommand.class);

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		WebElement dragFrom = findElement(driver);
		selmateContext.storeVariable(SelmateConstants.DRAG_DROP_VARIABLE_NAME, dragFrom);
		logger.info("END");
		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.DRAG_FROM.name();
	}
}
