
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.AbstractCustomCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.CustomCommandType;

public class CustomCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(CustomCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		CustomCommandType customCommandType = (CustomCommandType) abstractCommandType;
		try {
			AbstractCustomCommand baseCustomCommand = (AbstractCustomCommand) Class
					.forName(customCommandType.getCommandClass()).newInstance();
			populateCommonProperties(baseCustomCommand, customCommandType);
			for (Object obj : customCommandType.getArguments().getArgument()) {
				baseCustomCommand.addArgument(obj.toString());
			}
			logger.info("END");
			return baseCustomCommand;
		} catch (InstantiationException e) {
			throw new SelmateException(e);
		} catch (IllegalAccessException e) {
			throw new SelmateException(e);
		} catch (ClassNotFoundException e) {
			throw new SelmateException(e);
		}
	}

}
