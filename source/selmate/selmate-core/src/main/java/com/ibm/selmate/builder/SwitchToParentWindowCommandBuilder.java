
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SwitchToParentWindowCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class SwitchToParentWindowCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(SwitchToParentWindowCommand.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		SwitchToParentWindowCommand switchToParentWindowCommand = new SwitchToParentWindowCommand();
		populateCommonProperties(switchToParentWindowCommand, abstractCommandType);
		return switchToParentWindowCommand;
	}

}
