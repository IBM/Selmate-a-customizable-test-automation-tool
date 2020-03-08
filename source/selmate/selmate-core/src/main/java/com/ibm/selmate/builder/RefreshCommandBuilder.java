
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.RefreshCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public class RefreshCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(RefreshCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		RefreshCommand refreshCommand = new RefreshCommand();
		populateCommonProperties(refreshCommand, abstractCommandType);
		return refreshCommand;
	}

}
