
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SwitchToFrameCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToFrameCommandType;

public class SwitchToFrameCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = LogManager.getLogger(SwitchToFrameCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		SwitchToFrameCommandType switchToFrameCommandType = (SwitchToFrameCommandType) abstractCommandType;
		SwitchToFrameCommand switchToFrameCommand = new SwitchToFrameCommand();
		switchToFrameCommand.setId(switchToFrameCommandType.getId());
		switchToFrameCommand.setIndex(switchToFrameCommandType.getIndex());
		populateCommonProperties(switchToFrameCommand, switchToFrameCommandType);
		logger.info("END");

		return switchToFrameCommand;
	}

}
