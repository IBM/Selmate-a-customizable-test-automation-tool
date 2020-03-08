
package com.ibm.selmate.command;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ibm.selmate.SelmateContext;
import com.ibm.selmate.exception.SelmateExecutionException;
import com.ibm.selmate.report.ReportManager;
import com.ibm.selmate.util.CommandTypes;
import com.ibm.selmate.util.CommandValidationErrorHandler;

public class AssertCommand extends AbstractCommand {

	private String expectedValue;

	private String actualValue;

	private Type type;

	private ComparisonType comparisonType;

	private boolean warned;

	private String failureMsg;

	private Logger logger = Logger.getLogger(AssertCommand.class);

	public static enum Type {
		ERROR, WARN
	}

	public static enum ComparisonType {
		EQUAL, NOT_EQUAL
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public void setFailureMsg(String failureMsg) {
		if (failureMsg == null || failureMsg.trim().equals("")) {
			failureMsg = "FAILURE";
		}
		this.failureMsg = failureMsg;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setComparisonType(ComparisonType comparisonType) {
		this.comparisonType = comparisonType;
	}

	@Override
	public void validate(CommandValidationErrorHandler errorHandler, SelmateContext selmateContext) {

		logger.info("START");

		logger.info("this.failureMsg :-------------" + this.failureMsg);

		validateNullorBlank(errorHandler, "EXPECTED VALUE", this.expectedValue, selmateContext);
		validateNullorBlank(errorHandler, "ACTUAL VALUE", this.actualValue, selmateContext);

		logger.info("END");
	}

	public Boolean execute(WebDriver driver, SelmateContext selmateContext) throws SelmateExecutionException {

		logger.info("START");

		/*
		 * Evaluate expected value, actual value and failure message to replace referred
		 * variables with their values.
		 */
		if (expectedValue != null && expectedValue.trim().length() > 1) {
			expectedValue = selmateContext.evaluateVariables(expectedValue);
		}
		if (actualValue != null && actualValue.trim().length() > 1) {
			actualValue = selmateContext.evaluateVariables(actualValue);
		}
		if (failureMsg != null && failureMsg.trim().length() > 0) {
			failureMsg = selmateContext.evaluateVariables(failureMsg);
		}

		logger.info("assertType : " + this.type.name() + " comparisonType : " + this.comparisonType.name());
		logger.info(" expectedValue : " + expectedValue + " actualValue : " + actualValue);

		/*
		 * If the comparison type is EQUAL then Assert is considered to be successful if
		 * expected value and actual value are equal. In case the values are not same
		 * then the method returns false for ERROR type otherwise state of warned
		 * variable is set as true. If the comparison type is NOT EQUAL then Assert is
		 * considered to be successful if expected value and actual value are not equal.
		 * In case the values are equal then the method returns false for ERROR type
		 * otherwise state of warned variable is set as true.
		 */
		if (this.comparisonType == ComparisonType.EQUAL) {
			if (expectedValue.trim().equalsIgnoreCase(actualValue.trim())) {
				logger.info("Assert successful");
				return true;
			} else {
				if (this.type == Type.ERROR) {
					logger.fatal("ERROR: Assert Failed. Processing would be stopped.");
					return false;
				} else if (this.type == Type.WARN) {
					logger.fatal("WARN: Assert Failed. Processing would be continued.");
					this.warned = true;
				}
			}
		} else if (this.comparisonType == ComparisonType.NOT_EQUAL) {

			if (!expectedValue.trim().equalsIgnoreCase(actualValue.trim())) {
				return true;
			} else {
				if (this.type == Type.ERROR) {
					logger.fatal("ERROR: Assert Failed. Processing would be stopped.");
					return false;
				} else if (this.type == Type.WARN) {
					logger.fatal("WARN: Assert Failed. Processing would be continued.");
					this.warned = true;
				}
			}
		}
		logger.info("END");

		return true;

	}

	@Override
	public void log(boolean status, String stepDescription, SelmateContext selmateContext)
			throws SelmateExecutionException {
		logger.info("START");
		if (this.type == Type.ERROR) {
			ReportManager.getInstance().log(selmateContext.getCurrentStep(), getName(), getStepDescription(),
					status ? "PASSED" : "FAILED", status ? "" : failureMsg);
		} else if (this.type == Type.WARN) {
			ReportManager.getInstance().log(selmateContext.getCurrentStep(), getName(), getStepDescription(),
					warned ? "WARNED" : "PASSED", !warned ? "" : failureMsg);
		}
		logger.info("END");
	}

	@Override
	public String getName() {
		return CommandTypes.ASSERT.name() + "{" + this.comparisonType.name() + "}";
	}

}
