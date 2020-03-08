
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.ClickCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.ClickCommandType;

public class ClickCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(ClickCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		ClickCommandType clickCommandType = (ClickCommandType) abstractCommandType;
		ClickCommand clickCommand = new ClickCommand();
		populateCommonProperties(clickCommand, clickCommandType);
		clickCommand.setElement(buildElement(clickCommandType));
		logger.info("END");
		return clickCommand;
	}

}
