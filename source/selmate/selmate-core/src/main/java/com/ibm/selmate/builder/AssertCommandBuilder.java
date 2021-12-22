
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.selmate.command.AssertCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.AssertCommandType;

public class AssertCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(AssertCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) {
		logger.info("START");
		AssertCommandType assertCommandType = (AssertCommandType) abstractCommandType;
		AssertCommand assertCommand = new AssertCommand();
		populateCommonProperties(assertCommand, assertCommandType);
		assertCommand
				.setComparisonType(AssertCommand.ComparisonType.valueOf(assertCommandType.getComparisonType().name()));
		assertCommand.setType(AssertCommand.Type.valueOf(assertCommandType.getType().name()));
		assertCommand.setActualValue(assertCommandType.getActualValue());
		assertCommand.setExpectedValue(assertCommandType.getExpectedValue());
		assertCommand.setFailureMsg(assertCommandType.getFailureMsg());
		logger.info("END");

		return assertCommand;
	}

}
