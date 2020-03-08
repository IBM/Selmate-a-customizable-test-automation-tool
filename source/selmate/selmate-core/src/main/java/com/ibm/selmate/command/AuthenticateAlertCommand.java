
package com.ibm.selmate.command;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public class AuthenticateAlertCommand extends AbstractAlertCommand {

	private String userName;

	private String password;

	private Logger logger = Logger.getLogger(AuthenticateAlertCommand.class);

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {
		logger.info("START");
		validateNullorBlank(errorHandler, "USER NAME", userName, selmateContext);
		validateNullorBlank(errorHandler, "PASSWORD", password, selmateContext);
		logger.info("END");
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {
		logger.info("START");
		try {
			Robot robot = new Robot();

			// Type Username
			typeUsingRobot(userName);

			// press tab
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

			// Type Password
			typeUsingRobot(password);

			// press enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception ex) {
			throw new SelmateExecutionException(ex);
		}

		logger.info("END");

		return true;

	}

	public void typeUsingRobot(String str) throws SelmateExecutionException {

		try {
			logger.info("START");

			Robot robot = new Robot();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);

				if (Character.isDigit(c)) {
					robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
					robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
				} else if (Character.isLetter(c)) {
					if (Character.isUpperCase(c)) {
						robot.keyPress(KeyEvent.VK_SHIFT);
					}
					robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
					robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
					if (Character.isUpperCase(c)) {
						robot.keyRelease(KeyEvent.VK_SHIFT);
					}
				} else {

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
						logger.fatal("Charecter " + c + " not recognised");
						throw new SelmateExecutionException("Unhandled character " + c + " found");
					}

				}

			}
			logger.info("END");

		} catch (AWTException e) {
			throw new SelmateExecutionException(e);
		}
	}

	@Override
	public String getName() {
		return CommandTypes.AUTHENTICATE.name();
	}

}
