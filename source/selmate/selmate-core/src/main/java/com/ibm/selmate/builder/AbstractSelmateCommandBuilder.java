
package com.ibm.selmate.builder;

import com.ibm.selmate.command.AbstractCommand;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;

public abstract class AbstractSelmateCommandBuilder implements SelmateCommandBuilder {

	protected void populateCommonProperties(AbstractCommand abstractCommand, AbstractCommandType abstractCommandType) {
		abstractCommand.setStepDescription(abstractCommandType.getStepDescription());
	}
}
