
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.Element;
import com.ibm.selmate.command.ReadValueCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.ReadValueCommandType;

public class ReadValueCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = Logger.getLogger(ReadValueCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		ReadValueCommandType readValueCommandType = (ReadValueCommandType) abstractCommandType;
		ReadValueCommand readAttributeCommand = new ReadValueCommand();
		populateCommonProperties(readAttributeCommand, readValueCommandType);
		Element element = buildElement(readValueCommandType);
		readAttributeCommand.setElement(element);
		String variableName = readValueCommandType.getVariable();
		readAttributeCommand.setVariableName(variableName);
		readAttributeCommand.setAttributeName(readValueCommandType.getAttributeName());
		logger.info("END");

		return readAttributeCommand;
	}

}
