
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.AbstractWaitCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.DelayCommand;
import com.ibm.selmate.command.ImplicitWaitCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.DelayType;
import com.ibm.selmate.jaxb.stubs.TimeUnitType;
import com.ibm.selmate.jaxb.stubs.WaitCommandType;
import com.ibm.selmate.jaxb.stubs.WaitCommandType.Duration;

public class WaitCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(WaitCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		WaitCommandType waitCommandType = (WaitCommandType) abstractCommandType;
		DelayType type = waitCommandType.getType();
		AbstractWaitCommand waitCommand = null;
		if (type == DelayType.IMPLICIT) {
			waitCommand = new ImplicitWaitCommand();
		} else if (type == DelayType.INTERMEDIATE) {
			waitCommand = new DelayCommand();
		}
		buildWaitCommand(waitCommand, waitCommandType);
		populateCommonProperties(waitCommand, waitCommandType);
		logger.info("END");
		return waitCommand;
	}

	private void buildWaitCommand(AbstractWaitCommand waitCommand, WaitCommandType waitCommandType) {
		logger.info("START");
		Duration duration = waitCommandType.getDuration();
		long value = duration.getValue();
		TimeUnitType unit = duration.getUnit();
		waitCommand.setUnit(AbstractWaitCommand.Unit.valueOfStr(unit.value()));
		waitCommand.setTimeInterval(value);
		logger.info("END");
	}

}
