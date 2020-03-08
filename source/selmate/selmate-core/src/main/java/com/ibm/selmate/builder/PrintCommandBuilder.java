
package com.ibm.selmate.builder;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.PrintCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.PrintCommandType;

public class PrintCommandBuilder extends AbstractSelmateCommandBuilder {

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		PrintCommandType printCommandType = (PrintCommandType) abstractCommandType;
		PrintCommand printCommand = new PrintCommand();
		populateCommonProperties(printCommand, printCommandType);
		printCommand.setValue(printCommandType.getValue());

		return printCommand;
	}

}
