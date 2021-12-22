
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.SelmateConstants;

public class SelectOptionsCommand extends SelectCommand {

	List<Option> options = new ArrayList<Option>();

	private Logger logger = LogManager.getLogger(SelectOptionsCommand.class);

	public void setOptionList(List<Option> optionList) {
		this.options = optionList;
	}

	public void addOption(Option option) {
		this.options.add(option);
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		super.validate(errorHandler, selmateContext);
		if (options.size() == 0) {
			errorHandler.addErrorMessage(selmateContext.getCurrentStep(), "OPTION", SelmateConstants.OPTION_ERROR_MSG);
		}
		logger.info("END");
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {

		logger.info("START");

		WebElement webElement = findElement(driver);
		Select selectOptions = new Select(webElement);

		for (Option option : options) {
			Option.SelectionType selectionType = option.getSelectionType();
			String value = selmateContext.evaluateVariables(option.getValue());
			logger.info("selectionType" + selectionType);
			logger.info("value" + value);

			if (selectionType == Option.SelectionType.VISIBLE_TEXT) {
				if (this.isStatus()) {
					selectOptions.selectByVisibleText(value);
					// break;
				} else {
					selectOptions.deselectByVisibleText(value);
					// break;
				}
			} else if (selectionType == Option.SelectionType.INDEX) {
				if (this.isStatus()) {
					selectOptions.selectByIndex(Integer.parseInt(value));
					// break;
				} else {
					selectOptions.deselectByIndex(Integer.parseInt(value));
					// break;
				}
			} else if (selectionType == Option.SelectionType.VALUE) {
				if (this.isStatus()) {
					selectOptions.selectByValue(value);
					// break;
				} else {
					selectOptions.deselectByValue(value);
					// break;
				}
			}
		}
		logger.info("END");

		return true;
	}

	@Override
	public String getName() {
		return isStatus() ? CommandTypes.SELECT_OPTION.name() : CommandTypes.DESELECT_OPTION.name();
	}

}
