
package com.ibm.selmate.util;

import java.util.ArrayList;
import java.util.List;

public class CommandValidationErrorHandler {

	private static CommandValidationErrorHandler instance = new CommandValidationErrorHandler();

	private List<String> messages = new ArrayList<String>();

	private CommandValidationErrorHandler() {

	}

	public void addErrorMessage(int step, String fieldName, String errorMsg) {
		messages.add(String.format(SelmateConstants.VALIDATION_ERROR_MSG, step, fieldName, errorMsg));
	}

	public boolean isErrorPresent() {
		return messages.size() > 0;
	}

	public String getMessages() {
		StringBuilder errorMessagesStr = new StringBuilder();
		for (String message : messages) {
			errorMessagesStr.append(System.getProperty("line.separator"));
			errorMessagesStr.append(message);
		}
		errorMessagesStr.append(System.getProperty("line.separator"));
		return errorMessagesStr.toString();
	}

	public static CommandValidationErrorHandler getInstance() {
		if (instance == null) {
			synchronized (CommandValidationErrorHandler.class) {
				if (instance == null) {
					instance = new CommandValidationErrorHandler();
				}
			}
		}
		return instance;
	}

}
