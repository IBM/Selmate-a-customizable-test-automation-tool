
package com.ibm.selmate.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.selmate.builder.ActivateCommandBuilder;
import com.ibm.selmate.builder.AlertCommandBuilder;
import com.ibm.selmate.builder.AssertCommandBuilder;
import com.ibm.selmate.builder.AssignCommandBuilder;
import com.ibm.selmate.builder.ClickCommandBuilder;
import com.ibm.selmate.builder.CloseCommandBuilder;
import com.ibm.selmate.builder.CustomCommandBuilder;
import com.ibm.selmate.builder.DragCommandBuilder;
import com.ibm.selmate.builder.DropCommandBuilder;
import com.ibm.selmate.builder.NavigationHistoryCommandBuilder;
import com.ibm.selmate.builder.OpenURLCommandBuilder;
import com.ibm.selmate.builder.PrintCommandBuilder;
import com.ibm.selmate.builder.ReadStateCommandBuilder;
import com.ibm.selmate.builder.ReadValueCommandBuilder;
import com.ibm.selmate.builder.RefreshCommandBuilder;
import com.ibm.selmate.builder.ScreenshotCommandBuilder;
import com.ibm.selmate.builder.SelectCommandBuilder;
import com.ibm.selmate.builder.SelmateCommandBuilder;
import com.ibm.selmate.builder.SwitchToDefaultContentCommandBuilder;
import com.ibm.selmate.builder.SwitchToFrameCommandBuilder;
import com.ibm.selmate.builder.SwitchToParentWindowCommandBuilder;
import com.ibm.selmate.builder.SwitchToWindowCommandBuilder;
import com.ibm.selmate.builder.WaitCommandBuilder;
import com.ibm.selmate.builder.WritePasswordCommandBuilder;
import com.ibm.selmate.builder.WriteTextCommandBuilder;
import com.ibm.selmate.exception.SelmateException;
import com.ibm.selmate.jaxb.stubs.AbstractAlertCommandType;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.AbstractSelectCommandType;
import com.ibm.selmate.jaxb.stubs.ActivateCommandType;
import com.ibm.selmate.jaxb.stubs.AssertCommandType;
import com.ibm.selmate.jaxb.stubs.AssignCommandType;
import com.ibm.selmate.jaxb.stubs.ClickCommandType;
import com.ibm.selmate.jaxb.stubs.CloseCommandType;
import com.ibm.selmate.jaxb.stubs.CustomCommandType;
import com.ibm.selmate.jaxb.stubs.DragCommandType;
import com.ibm.selmate.jaxb.stubs.DropCommandType;
import com.ibm.selmate.jaxb.stubs.NavigationHistoryCommandType;
import com.ibm.selmate.jaxb.stubs.OpenURLCommandType;
import com.ibm.selmate.jaxb.stubs.PrintCommandType;
import com.ibm.selmate.jaxb.stubs.ReadStateCommandType;
import com.ibm.selmate.jaxb.stubs.ReadValueCommandType;
import com.ibm.selmate.jaxb.stubs.RefreshCommandType;
import com.ibm.selmate.jaxb.stubs.ScreenshotCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToDefaultContentCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToFrameCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToParentWindowCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToWindowCommandType;
import com.ibm.selmate.jaxb.stubs.WaitCommandType;
import com.ibm.selmate.jaxb.stubs.WritePasswordCommandType;
import com.ibm.selmate.jaxb.stubs.WriteTextCommandType;

/**
 * This class represents the factory for Selmate Command builder.
 * 
 * @author Avijit Basak
 * 
 */
public class SelmateCommandBuilderFactory {

	private static final SelmateCommandBuilderFactory instance = new SelmateCommandBuilderFactory();

	private Logger logger = LogManager.getLogger(SelmateCommandBuilderFactory.class);

	private SelmateCommandBuilderFactory() {

	}

	/**
	 * This operation returns an instance of {@link SelmateCommandBuilder}.
	 * 
	 * @param commandType
	 * @return {@link SelmateCommandBuilder}
	 * @throws SelmateException
	 */
	public SelmateCommandBuilder createCommandBuilder(AbstractCommandType commandType) throws SelmateException {

		logger.info("START");

		if (AbstractAlertCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new AlertCommandBuilder();
		} else if (AssertCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new AssertCommandBuilder();
		} else if (AssignCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new AssignCommandBuilder();
		} else if (AbstractSelectCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new SelectCommandBuilder();
		} else if (WaitCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new WaitCommandBuilder();
		} else if (ActivateCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new ActivateCommandBuilder();
		} else if (ClickCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new ClickCommandBuilder();
		} else if (DragCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new DragCommandBuilder();
		} else if (DropCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new DropCommandBuilder();
		} else if (NavigationHistoryCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new NavigationHistoryCommandBuilder();
		} else if (OpenURLCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new OpenURLCommandBuilder();
		} else if (ReadValueCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new ReadValueCommandBuilder();
		} else if (ReadStateCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new ReadStateCommandBuilder();
		} else if (RefreshCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new RefreshCommandBuilder();
		} else if (SwitchToFrameCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new SwitchToFrameCommandBuilder();
		} else if (SwitchToWindowCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new SwitchToWindowCommandBuilder();
		} else if (SwitchToParentWindowCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new SwitchToParentWindowCommandBuilder();
		} else if (SwitchToDefaultContentCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new SwitchToDefaultContentCommandBuilder();
		} else if (WriteTextCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new WriteTextCommandBuilder();
		} else if (WritePasswordCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new WritePasswordCommandBuilder();
		} else if (CustomCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new CustomCommandBuilder();
		} else if (ScreenshotCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new ScreenshotCommandBuilder();
		} else if (PrintCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new PrintCommandBuilder();
		} else if (CloseCommandType.class.isAssignableFrom(commandType.getClass())) {
			return new CloseCommandBuilder();
		}
		logger.info("END");
		throw new SelmateException("Unhandled Command Type : " + commandType.getClass().getName());
	}

	public static final SelmateCommandBuilderFactory getInstance() {
		return instance;
	}

}
