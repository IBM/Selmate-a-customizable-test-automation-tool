
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.AssignCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.AssignCommandType;

public class AssignCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(AssignCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) {
		logger.info("START");
		AssignCommandType assignCommandType = (AssignCommandType) abstractCommandType;
		AssignCommand assignCommand = new AssignCommand();
		populateCommonProperties(assignCommand, assignCommandType);
		assignCommand.setValue(assignCommandType.getValue());
		assignCommand.setVariableName(assignCommandType.getVariable());
		logger.info("END");
		return assignCommand;
	}

}
