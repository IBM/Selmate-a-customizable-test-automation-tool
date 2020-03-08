
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.Element;
import com.ibm.selmate.command.ReadStateCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.ReadStateCommandType;

public class ReadStateCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(ReadStateCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		ReadStateCommandType readStateCommandType = (ReadStateCommandType) abstractCommandType;
		ReadStateCommand readStateCommand = new ReadStateCommand();
		populateCommonProperties(readStateCommand, readStateCommandType);
		Element element = buildElement(readStateCommandType);
		readStateCommand.setElement(element);
		readStateCommand.setState(ReadStateCommand.State.valueOf(readStateCommandType.getState()));
		readStateCommand.setVariableName(readStateCommandType.getVariable());
		logger.info("END");

		return readStateCommand;
	}

}
