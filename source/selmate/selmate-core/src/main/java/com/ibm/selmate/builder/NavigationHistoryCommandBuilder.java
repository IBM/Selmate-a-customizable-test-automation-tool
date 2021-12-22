
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.AbstractCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.NavigateBackwardCommand;
import com.ibm.selmate.command.NavigateForwardCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.NavigationHistoryCommandType;
import com.ibm.selmate.jaxb.stubs.NavigationType;

public class NavigationHistoryCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(NavigationHistoryCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		NavigationHistoryCommandType navigationHistoryCommandType = (NavigationHistoryCommandType) abstractCommandType;
		AbstractCommand navigationCommand = null;
		NavigationType type = navigationHistoryCommandType.getType();
		if (type == NavigationType.FORWARD) {
			navigationCommand = new NavigateForwardCommand();
		} else if (type == NavigationType.BACKWARD) {
			navigationCommand = new NavigateBackwardCommand();
		} else {
			throw new SelmateException("Invalid Navigation Direction");
		}
		populateCommonProperties(navigationCommand, abstractCommandType);
		logger.info("END");
		return navigationCommand;
	}

}
