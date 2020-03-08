
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.ScreenshotCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class ScreenshotCommandBuilder extends AbstractSelmateCommandBuilder {

	Logger logger = Logger.getLogger(ScreenshotCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		ScreenshotCommand screenshotCommand = new ScreenshotCommand();
		populateCommonProperties(screenshotCommand, abstractCommandType);
		return screenshotCommand;
	}
}
