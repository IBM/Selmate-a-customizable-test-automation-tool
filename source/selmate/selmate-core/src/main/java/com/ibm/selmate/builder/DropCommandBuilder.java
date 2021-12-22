
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.DropCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.DropCommandType;

public class DropCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = LogManager.getLogger(DropCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		DropCommandType dropCommandType = (DropCommandType) abstractCommandType;
		DropCommand dropCommand = new DropCommand();
		populateCommonProperties(dropCommand, dropCommandType);
		dropCommand.setElement(buildElement(dropCommandType));
		logger.info("END");

		return dropCommand;
	}

}
