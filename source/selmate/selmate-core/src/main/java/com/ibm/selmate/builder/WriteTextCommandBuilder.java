
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.WriteCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.WriteTextCommandType;

public class WriteTextCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = LogManager.getLogger(WriteTextCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		WriteTextCommandType writeTextCommandType = (WriteTextCommandType) abstractCommandType;
		WriteCommand writeCommand = new WriteCommand();
		writeCommand.setElement(buildElement(writeTextCommandType));
		writeCommand.setContent(writeTextCommandType.getValue());
		populateCommonProperties(writeCommand, writeTextCommandType);
		logger.info("END");

		return writeCommand;
	}

}
