
package com.ibm.selmate.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.command.AcceptAlertCommand;
import com.ibm.selmate.command.AuthenticateAlertCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.DismissAlertCommand;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractAlertCommandType;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.AcceptAlertCommandType;
import com.ibm.selmate.jaxb.stubs.AuthenticateAlertCommandType;
import com.ibm.selmate.jaxb.stubs.DismissAlertCommandType;

public class AlertCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = LogManager.getLogger(AlertCommandBuilder.class);

	@Override
	public Command build(AbstractCommandType abstractCommandType) throws SelmateException {
		logger.info("START");
		AbstractAlertCommandType alertCommandType = (AbstractAlertCommandType) abstractCommandType;
		if (AcceptAlertCommandType.class.isAssignableFrom(alertCommandType.getClass())) {
			AcceptAlertCommand acceptAlertCommand = new AcceptAlertCommand();
			populateCommonProperties(acceptAlertCommand, alertCommandType);
			return acceptAlertCommand;
		} else if (DismissAlertCommandType.class.isAssignableFrom(alertCommandType.getClass())) {
			DismissAlertCommand dismissAlertCommand = new DismissAlertCommand();
			populateCommonProperties(dismissAlertCommand, alertCommandType);
			return dismissAlertCommand;
		} else if (AuthenticateAlertCommandType.class.isAssignableFrom(alertCommandType.getClass())) {
			AuthenticateAlertCommandType authenticate = (AuthenticateAlertCommandType) alertCommandType;
			if (null != authenticate) {
				AuthenticateAlertCommand authenticateAlertCommand = new AuthenticateAlertCommand();
				populateCommonProperties(authenticateAlertCommand, alertCommandType);
				authenticateAlertCommand.setUserName(authenticate.getUser());
				authenticateAlertCommand.setPassword(authenticate.getPwd());
				return authenticateAlertCommand;
			}
		}
		logger.info("END");
		throw new SelmateException("Invalid Alert Command type");
	}

}
