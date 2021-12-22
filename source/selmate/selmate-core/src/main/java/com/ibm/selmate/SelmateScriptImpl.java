
package com.ibm.selmate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.ImeActivationFailedException;
import org.openqa.selenium.ImeNotAvailableException;
import org.openqa.selenium.InvalidCookieDomainException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SelmateScript;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.exception.SelmateValidationException;
import com.ibm.selmate.report.ReportManager;
import com.ibm.selmate.report.VideoRecordingManager;
import com.ibm.selmate.util.CommandValidationErrorHandler;
import com.ibm.selmate.util.ExceptionTypes;
import com.ibm.selmate.util.MessageUtil;

public final class SelmateScriptImpl implements SelmateScript {

	static final Logger logger = LogManager.getLogger(SelmateScriptImpl.class);

	private String name;

	private List<Command> commands = new ArrayList<Command>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCommand(Command command) {
		this.commands.add(command);
	}

	public Iterator<Command> iterateCommand() {
		return commands.iterator();
	}

	/**
	 * This operation validates all the commands.
	 */
	public void validate() throws SelmateValidationException {
		logger.info("START");
		SelmateContextImpl selmateContextImpl = SelmateContextImpl.getCurrentContext();
		selmateContextImpl.resetStep();
		CommandValidationErrorHandler commandValidationErrorHandler = CommandValidationErrorHandler.getInstance();
		for (Command command : commands) {
			logger.info("Current Command being validated is :" + command.getClass().toString());
			selmateContextImpl.incrementCurrentStep();
			command.validate(commandValidationErrorHandler, SelmateContextAdapter.getCurrentContext());
		}
		if (commandValidationErrorHandler.isErrorPresent()) {
			throw new SelmateValidationException(commandValidationErrorHandler.getMessages());
		}
		logger.info("END");
	}

	/**
	 * This operation executes the entire script.
	 */
	public Boolean execute(WebDriver driver) throws SelmateExecutionException {

		logger.info("START");

		ReportManager logManager = null;
		boolean status = false;
		VideoRecordingManager recordingMgr = null;
		try {

			/*
			 * Initialize the Log manager and Video recorder.
			 */
			logManager = ReportManager.getInstance();
			SelmateContext selmateContext = SelmateContextAdapter.getCurrentContext();
			logManager.init(selmateContext);
			recordingMgr = VideoRecordingManager.getInstance();
			try {
				recordingMgr.setup();
			} catch (Exception e) {
				logger.fatal("ERROR while initializing video recording.", e);
				throw new SelmateExecutionException(e);
			}

			/*
			 * Reset the step count. Iterate and execute all the commands present. If any
			 * command returns false or throws any Exception then the process will be
			 * stopped and rest of the commands should be executed.
			 */
			SelmateContextImpl selmateContextImpl = SelmateContextImpl.getCurrentContext();
			selmateContextImpl.resetStep();
			for (Command command : commands) {
				try {
					logger.info("Executing Command : " + command.getName());
					selmateContextImpl.incrementCurrentStep();
					status = command.execute(driver, SelmateContextAdapter.getCurrentContext());
					command.log(status, command.getStepDescription(), selmateContext);

					// Introduce a delay between two consecutive commands.
					Thread.sleep(1000);
					if (!status) {
						break;
					}
				} catch (Exception ex) {
					while (ex.getCause() != null) {
						ex = (Exception) ex.getCause();
					}
					logger.fatal("Exception occured while executing " + command.getName() + " : " + ex);
					logError(command, driver, ex);

					throw new SelmateExecutionException(ex);
				}
				status = false;
			}
			logger.info("End of execute method inside  SelmateScriptImpl");
		} finally {
			/*
			 * Release all resources.
			 */
			if (logManager != null) {
				logManager.finish();
			}
			try {
				if (recordingMgr != null) {
					recordingMgr.Close();
				}
			} catch (Exception e) {
				logger.fatal("ERROR while closing video recording.", e);
			}
		}

		logger.info("END");

		return status;

	}

	/**
	 * This operation logs error messages to the output report depending on the type
	 * of exception encountered.
	 * 
	 * @param command
	 * @param driver
	 * @param ex
	 */
	public void logError(Command command, WebDriver driver, Exception ex) {

		logger.info("START");

		MessageUtil messageUtil = MessageUtil.getInstance();
		if (ex instanceof ElementNotVisibleException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.ELEMENT_NOT_VISIBLE_ERROR));
		} else if (ex instanceof ImeActivationFailedException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.IME_ACTIVATION_FAILED_ERROR));
		} else if (ex instanceof ImeNotAvailableException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.IME_NOT_SUPPORTED_ERROR));
		} else if (ex instanceof InvalidCookieDomainException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.INVALID_COOKIE_DOMAIN_ERROR));
		} else if (ex instanceof InvalidElementStateException) {
			addErrorLogInReport(command, driver, ExceptionTypes.INVALID_ELEMENT_STATE_ERROR);
		} else if (ex instanceof InvalidSelectorException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.INVALID_SELECTOR_ERROR));
		} else if (ex instanceof MoveTargetOutOfBoundsException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.INVALID_TARGET_ERROR));
		} else if (ex instanceof NoAlertPresentException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.NO_ALERT_PRESENT_ERROR));
		} else if (ex instanceof NoSuchElementException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.NO_SUCH_ELEMENT_ERROR));
		} else if (ex instanceof NoSuchFrameException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.NO_SUCH_FRAME_ERROR));
		} else if (ex instanceof NoSuchWindowException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.NO_SUCH_WINDOW_ERROR));
		} else if (ex instanceof StaleElementReferenceException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.STALE_ELEMENT_REFERENCE_ERROR));
		} else if (ex instanceof TimeoutException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.TIMEOUT_ERROR));
		} else if (ex instanceof UnableToSetCookieException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.UNABLE_TO_SET_COOKIE_ERROR));
		} else if (ex instanceof UnexpectedTagNameException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.UNEXPECTED_TAG_NAME_ERROR));
		} else if (ex instanceof WebDriverException) {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.WEBDRIVER_ERROR));
		} else {
			addErrorLogInReport(command, driver, messageUtil.getMessage(ExceptionTypes.UNKNOWN_ERROR));
		}

		logger.info("END");

	}

	/**
	 * This operation add the error message to the Selmate report along with
	 * screenshot.
	 * 
	 * @param command
	 * @param driver
	 * @param errorMessage
	 */
	public void addErrorLogInReport(Command command, WebDriver driver, String errorMessage) {
		logger.info("START");
		try {
			if (TakesScreenshot.class.isAssignableFrom(driver.getClass())) {
				byte[] imgData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				ReportManager.getInstance().log(SelmateContextAdapter.getCurrentContext().getCurrentStep(),
						command.getName(), command.getStepDescription(), "FAILED", imgData, errorMessage);
			}
		} catch (Exception e) {
			logger.fatal("ERROR while logging Exception", e);
		}
		logger.info("END");
	}

}
