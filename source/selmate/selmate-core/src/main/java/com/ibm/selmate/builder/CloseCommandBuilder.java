package com.ibm.selmate.builder;

import com.ibm.selmate.command.CloseCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class CloseCommandBuilder implements SelmateCommandBuilder {

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		CloseCommand closeCommand = new CloseCommand();
		closeCommand.setStepDescription(abstractCommandType.getStepDescription());
		return closeCommand;
	}

}