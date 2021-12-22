
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.SelectCommand;
import com.ibm.selmate.command.SelectOptionsCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.SelectCommandType;
import com.ibm.selmate.jaxb.stubs.SelectOptionsCommandType;
import com.ibm.selmate.jaxb.stubs.SelectOptionsCommandType.Options.Option;

public class SelectCommandBuilder extends AbstractElementInteractionCommandBuilder {

	private Logger logger = LogManager.getLogger(SelectCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType commandType) throws SelmateException {
		logger.info("START");
		SelectCommand selectCommand = null;
		if (SelectCommandType.class.isAssignableFrom(commandType.getClass())) {
			SelectCommandType selectCommandType = (SelectCommandType) commandType;
			selectCommand = new SelectCommand();
			selectCommand.setElement(buildElement(selectCommandType));
			selectCommand.setStatus(getStatus(selectCommandType));

		} else if (SelectOptionsCommandType.class.isAssignableFrom(commandType.getClass())) {
			SelectOptionsCommandType selectOptionsCommandType = (SelectOptionsCommandType) commandType;
			selectCommand = new SelectOptionsCommand();
			selectCommand.setElement(buildElement(selectOptionsCommandType));
			selectCommand.setStatus(getStatus(selectOptionsCommandType));

			buildOptions(selectOptionsCommandType, (SelectOptionsCommand) selectCommand);

		}
		populateCommonProperties(selectCommand, commandType);
		logger.info("END");
		return selectCommand;
	}

	private void buildOptions(SelectOptionsCommandType selectOptionsCommandType,
			SelectOptionsCommand selectOptionsCommand) {
		for (Option optionType : selectOptionsCommandType.getOptions().getOption()) {
			com.ibm.selmate.command.Option option = new com.ibm.selmate.command.Option();
			option.setSelectionType(com.ibm.selmate.command.Option.SelectionType.valueOf(optionType.getBy().name()));
			option.setValue(optionType.getValue());
			selectOptionsCommand.addOption(option);
		}
	}

}
