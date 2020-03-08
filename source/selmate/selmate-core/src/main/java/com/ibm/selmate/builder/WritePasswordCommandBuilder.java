
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.WritePasswordCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.WritePasswordCommandType;

public class WritePasswordCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(WritePasswordCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");

		WritePasswordCommandType writePasswordCommandType = (WritePasswordCommandType) abstractCommandType;
		WritePasswordCommand writePasswordCommand = new WritePasswordCommand();
		writePasswordCommand.setContent(writePasswordCommandType.getValue());
		writePasswordCommand.setElement(buildElement(writePasswordCommandType));
		populateCommonProperties(writePasswordCommand, writePasswordCommandType);

		logger.info("END");

		return writePasswordCommand;
	}

}
