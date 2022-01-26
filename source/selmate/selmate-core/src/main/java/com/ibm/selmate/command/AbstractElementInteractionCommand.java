
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
		String[] locators = locatorValue.split(Locator.SEPARATOR);

		if (locatorType == Locator.Type.ID) {
			webElement = driver.findElement(By.id(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.id(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.XPATH) {
			webElement = driver.findElement(By.xpath(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.xpath(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.CLASS_NAME) {
			webElement = driver.findElement(By.className(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.className(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.TAG_NAME) {
			webElement = driver.findElement(By.tagName(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.tagName(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.CSS_SELECTOR) {
			webElement = driver.findElement(By.cssSelector(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.cssSelector(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.LINKTEXT) {
			webElement = driver.findElement(By.linkText(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.linkText(locators[i]));
				}
			}
		} else if (locatorType == Locator.Type.PARTIAL_LINKTEXT) {
			webElement = driver.findElement(By.partialLinkText(locatorValue));
			if (locators.length > 1) {
				for (int i = 1; i < locators.length; i++) {
					webElement = webElement.findElement(By.partialLinkText(locators[i]));
				}
			}
		}
		logger.info("END");

		return webElement;
	}

}