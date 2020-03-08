
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.OpenCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.OpenURLCommandType;

public class OpenURLCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(OpenURLCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		OpenURLCommandType openURLCommandType = (OpenURLCommandType) abstractCommandType;
		OpenCommand openCommand = new OpenCommand();
		populateCommonProperties(openCommand, openURLCommandType);
		openCommand.setUrl(openURLCommandType.getUrl());
		logger.info("END");

		return openCommand;
	}

}
