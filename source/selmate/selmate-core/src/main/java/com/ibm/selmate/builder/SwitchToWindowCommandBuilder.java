
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SwitchToWindowCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class SwitchToWindowCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(SwitchToWindowCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		SwitchToWindowCommand switchToWindowCommand = new SwitchToWindowCommand();
		populateCommonProperties(switchToWindowCommand, abstractCommandType);
		return switchToWindowCommand;
	}

}
