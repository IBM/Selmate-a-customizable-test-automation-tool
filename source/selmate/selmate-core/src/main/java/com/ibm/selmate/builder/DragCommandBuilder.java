
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.DragCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.DragCommandType;

public class DragCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(DragCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		DragCommandType dragCommandType = (DragCommandType) abstractCommandType;
		DragCommand dragCommand = new DragCommand();
		populateCommonProperties(dragCommand, dragCommandType);
		dragCommand.setElement(buildElement(dragCommandType));
		logger.info("END");

		return dragCommand;
	}

}
