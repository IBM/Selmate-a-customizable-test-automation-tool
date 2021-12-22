
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SwitchToDefultContentCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class SwitchToDefaultContentCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(SwitchToDefaultContentCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		SwitchToDefultContentCommand switchToDefultContentCommand = new SwitchToDefultContentCommand();
		populateCommonProperties(switchToDefultContentCommand, abstractCommandType);
		return switchToDefultContentCommand;
	}

}
