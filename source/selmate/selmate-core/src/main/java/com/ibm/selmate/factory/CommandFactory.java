
package com.ibm.selmate.factory;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.AcceptAlertCommand;
import com.ibm.selmate.command.ActivateCommand;
import com.ibm.selmate.command.AssertCommand;
import com.ibm.selmate.command.AssignCommand;
import com.ibm.selmate.command.AuthenticateAlertCommand;
import com.ibm.selmate.command.ClickCommand;
import com.ibm.selmate.command.Command;
import com.ibm.selmate.command.DelayCommand;
import com.ibm.selmate.command.DismissAlertCommand;
import com.ibm.selmate.command.DragCommand;
import com.ibm.selmate.command.DropCommand;
import com.ibm.selmate.command.ImplicitWaitCommand;
import com.ibm.selmate.command.NavigateBackwardCommand;
import com.ibm.selmate.command.NavigateForwardCommand;
import com.ibm.selmate.command.OpenCommand;
import com.ibm.selmate.command.ReadStateCommand;
import com.ibm.selmate.command.ReadValueCommand;
import com.ibm.selmate.command.RefreshCommand;
import com.ibm.selmate.command.SelectCommand;
import com.ibm.selmate.command.SelectOptionsCommand;
import com.ibm.selmate.command.SwitchToDefultContentCommand;
import com.ibm.selmate.command.SwitchToFrameCommand;
import com.ibm.selmate.command.SwitchToWindowCommand;
import com.ibm.selmate.command.WriteCommand;
import com.ibm.selmate.command.WritePasswordCommand;

/**
 * This class represents the Command Factory used for creating Selmate commands.
 * 
 * @author Avijit Basak
 * 
 */
public class CommandFactory {

	private static final CommandFactory factory = new CommandFactory();

	private Logger logger = Logger.getLogger(CommandFactory.class);

	private CommandFactory() {

	}

	public static CommandFactory getInstance() {

		return factory;
	}

	public static enum Type {
		ACCEPT_ALERT_COMMAND, ACTIVATE_COMMAND, ASSERT_COMMAND, ASSIGN_COMMAND, AUTHENTICATE_COMMAND, CLICK_COMMAND, DELAY_COMMAND, DISMISS_ALERT_COMMAND, DRAG_FROM_COMMAND, DROP_TO_COMMAND, IMPLICIT_WAIT_COMMAND_TYPE, BACKWARD_HIST_NAV_COMMAND_TYPE, FORWARD_HIST_NAV_COMMAND_TYPE, OPEN_COMMAND, READVALUE_COMMAND, READSTATE_COMMAND, REFRESH_COMMAND, SELECT_OPTION_COMMAND, SWITCH_FRAME_COMMAND, SWITCH_WINDOW_COMMAND, SWITCH_DEFULT_CONTENT_COMMAND, WRITE_COMMAND, WRITE_PWD_COMMAND, SELECT_STATUS
	}

	/**
	 * This operation returns an instance of Selmate {@link Command} as per the
	 * mentioned argument {@link Type}
	 * 
	 * @param type
	 * @return {@link Command}
	 */
	public Command create(Type type) {

		logger.info("START");
		Command command = null;

		if (type == Type.ACCEPT_ALERT_COMMAND) {
			command = new AcceptAlertCommand();
		}
		if (type == Type.ACTIVATE_COMMAND) {
			command = new ActivateCommand();
		}
		if (type == Type.ASSERT_COMMAND) {
			command = new AssertCommand();
		}
		if (type == Type.ASSIGN_COMMAND) {
			command = new AssignCommand();
		}
		if (type == Type.AUTHENTICATE_COMMAND) {
			command = new AuthenticateAlertCommand();
		}
		if (type == Type.CLICK_COMMAND) {
			command = new ClickCommand();
		}
		if (type == Type.DELAY_COMMAND) {
			command = new DelayCommand();
		}
		if (type == Type.DISMISS_ALERT_COMMAND) {
			command = new DismissAlertCommand();
		}
		if (type == Type.DRAG_FROM_COMMAND) {
			command = new DragCommand();
		}
		if (type == Type.DROP_TO_COMMAND) {
			command = new DropCommand();
		}
		if (type == Type.IMPLICIT_WAIT_COMMAND_TYPE) {
			command = new ImplicitWaitCommand();
		}
		if (type == Type.BACKWARD_HIST_NAV_COMMAND_TYPE) {
			command = new NavigateBackwardCommand();
		}
		if (type == Type.FORWARD_HIST_NAV_COMMAND_TYPE) {
			command = new NavigateForwardCommand();
		}
		if (type == Type.OPEN_COMMAND) {
			command = new OpenCommand();
		}
		if (type == Type.READVALUE_COMMAND) {
			command = new ReadValueCommand();
		}
		if (type == Type.READSTATE_COMMAND) {
			command = new ReadStateCommand();
		}
		if (type == Type.REFRESH_COMMAND) {
			command = new RefreshCommand();
		}
		if (type == Type.SELECT_OPTION_COMMAND) {
			command = new SelectOptionsCommand();
		}
		if (type == Type.SWITCH_FRAME_COMMAND) {
			command = new SwitchToFrameCommand();
		}
		if (type == Type.SWITCH_WINDOW_COMMAND) {
			command = new SwitchToWindowCommand();
		}
		if (type == Type.SWITCH_DEFULT_CONTENT_COMMAND) {
			command = new SwitchToDefultContentCommand();
		}
		if (type == Type.WRITE_COMMAND) {
			command = new WriteCommand();
		}
		if (type == Type.WRITE_PWD_COMMAND) {
			command = new WritePasswordCommand();
		}
		if (type == Type.SELECT_STATUS) {
			command = new SelectCommand();
		}
		// if
		// (type == Type.CUSTOM_ACTION_COMMAND){
		// command = new BaseCustomCommand();
		// }

		logger.info("END");

		return command;
	}

}
