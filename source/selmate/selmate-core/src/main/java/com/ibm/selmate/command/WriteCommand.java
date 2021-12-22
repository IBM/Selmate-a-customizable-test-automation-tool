
package com.ibm.selmate.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.DriverDetector;

public class WriteCommand extends AbstractInputCommand {

	private String content;

	private Logger logger = LogManager.getLogger(WriteCommand.class);

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		super.validate(errorHandler, selmateContext);
		if (this.content == null) {
			errorHandler.addErrorMessage(selmateContext.getCurrentStep(), "Content", "Cannot write NULL value.");
		}
		logger.info("END");
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");

		WebElement webElement = findElement(driver);
		if (content.startsWith("$")) {
			String variableName = content.substring(1, content.length());
			content = (String) selmateContext.getVariableValue(variableName.trim());
		}
		logger.info("content : " + content);

		webElement.clear();

		if (DriverDetector.isIEDriver()) {
			logger.info("Driver Type: IE");
			webElement.click();
			typeUsingRobot(content);
		} else {
			logger.info("Driver Type: OTHER");
			webElement.sendKeys(content);
		}

		logger.info("END");

		return true;

	}

	@Override
	public String getName() {
		return CommandTypes.WRITE.name();
	}

	public void typeUsingRobot(String str) {

		Robot robot;
		try {
			robot = new Robot();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);

				if (Character.isDigit(c)) {
					// logger.info("its a digit : " + c);

					robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
					robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));

				} else if (Character.isLetter(c)) {

					// logger.info("its a letter : " + c);

					if (Character.isUpperCase(c)) {
						robot.keyPress(KeyEvent.VK_SHIFT);
					}

					robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
					robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));

					if (Character.isUpperCase(c)) {
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}

				} else {
					// logger.info("its a special charecter : " + c);

					switch (c) {

					case '`':
						robot.keyPress(192);
						robot.keyRelease(192);
						break;

					case '@':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '~':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(192);
						robot.keyRelease(192);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '!':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(49);
						robot.keyRelease(49);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '#':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '$':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '%':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '^':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '&':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '*':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '(':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case ')':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_0);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '_':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '-':
						robot.keyPress(KeyEvent.VK_MINUS);
						robot.keyRelease(KeyEvent.VK_MINUS);
						break;

					case '+':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '=':
						robot.keyPress(KeyEvent.VK_EQUALS);
						robot.keyRelease(KeyEvent.VK_EQUALS);
						break;

					case '{':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '[':
						robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
						robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
						break;

					case '}':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case ']':
						robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
						robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
						break;

					case '|':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '\\':
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						break;

					case ':':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case ';':
						robot.keyPress(KeyEvent.VK_SEMICOLON);
						robot.keyRelease(KeyEvent.VK_SEMICOLON);
						break;

					case '"':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '\'':
						robot.keyPress(KeyEvent.VK_QUOTE);
						robot.keyRelease(KeyEvent.VK_QUOTE);
						break;

					case '<':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case ',':
						robot.keyPress(KeyEvent.VK_COMMA);
						robot.keyRelease(KeyEvent.VK_COMMA);
						break;

					case '>':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '.':
						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
						break;

					case '?':
						robot.keyPress(KeyEvent.VK_SHIFT);
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_SHIFT);
						break;

					case '/':
						robot.keyPress(KeyEvent.VK_BACK_SLASH);
						robot.keyRelease(KeyEvent.VK_BACK_SLASH);
						break;

					default:
						// logger.info("Charecter "+c+" not recognised");

					}

				}

			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block

		}
	}

}
