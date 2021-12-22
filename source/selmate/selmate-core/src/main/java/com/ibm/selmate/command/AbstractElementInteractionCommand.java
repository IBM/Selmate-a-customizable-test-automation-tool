
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public abstract class AbstractElementInteractionCommand extends AbstractCommand {

	private Element element;

	private Logger logger = LogManager.getLogger(AbstractElementInteractionCommand.class);

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		validateNullorBlank(errorHandler, "ELEMENT LOCATOR", element.getLocator().getValue(), selmateContext);
		logger.info("END");
	}

	protected WebElement findElement(WebDriver driver) {
		logger.info("START");

		WebElement webElement = null;
		String locatorValue = element.getLocator().getValue();
		Locator.Type locatorType = element.getLocator().getType();

		if (locatorType == Locator.Type.ID) {
			webElement = driver.findElement(By.id(locatorValue));
		} else if (locatorType == Locator.Type.XPATH) {
			webElement = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType == Locator.Type.CLASS_NAME) {
			webElement = driver.findElement(By.className(locatorValue));
		} else if (locatorType == Locator.Type.TAG_NAME) {
			webElement = driver.findElement(By.tagName(locatorValue));
		} else if (locatorType == Locator.Type.CSS_SELECTOR) {
			webElement = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType == Locator.Type.LINKTEXT) {
			webElement = driver.findElement(By.linkText(locatorValue));
		} else if (locatorType == Locator.Type.PARTIAL_LINKTEXT) {
			webElement = driver.findElement(By.partialLinkText(locatorValue));
		}
		logger.info("END");

		return webElement;
	}

}