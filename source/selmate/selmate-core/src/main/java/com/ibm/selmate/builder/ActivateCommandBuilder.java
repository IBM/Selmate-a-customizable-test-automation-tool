
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.ActivateCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.ActivateCommandType;

public class ActivateCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(ActivateCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		ActivateCommandType activateCommandType = (ActivateCommandType) abstractCommandType;
		ActivateCommand activateCommand = new ActivateCommand();
		populateCommonProperties(activateCommand, activateCommandType);
		activateCommand.setElement(buildElement(activateCommandType));
		logger.info("END");
		return activateCommand;
	}

}
