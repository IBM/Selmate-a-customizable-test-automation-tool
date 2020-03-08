
package com.ibm.selmate.adapter.xls;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.ibm.selmate.adapter.xls.exception.SelmateXLSAdapterException;
import com.ibm.selmate.adapter.xls.util.SelmateConstants;
import com.ibm.selmate.jaxb.stubs.AbstractCommandType;
import com.ibm.selmate.jaxb.stubs.AcceptAlertCommandType;
import com.ibm.selmate.jaxb.stubs.ActivateCommandType;
import com.ibm.selmate.jaxb.stubs.AssertCommandType;
import com.ibm.selmate.jaxb.stubs.AssertType;
import com.ibm.selmate.jaxb.stubs.AssignCommandType;
import com.ibm.selmate.jaxb.stubs.AuthenticateAlertCommandType;
import com.ibm.selmate.jaxb.stubs.ClickCommandType;
import com.ibm.selmate.jaxb.stubs.CloseCommandType;
import com.ibm.selmate.jaxb.stubs.CustomCommandType;
import com.ibm.selmate.jaxb.stubs.DelayType;
import com.ibm.selmate.jaxb.stubs.DismissAlertCommandType;
import com.ibm.selmate.jaxb.stubs.DragCommandType;
import com.ibm.selmate.jaxb.stubs.DropCommandType;
import com.ibm.selmate.jaxb.stubs.ElementLocatorType;
import com.ibm.selmate.jaxb.stubs.ElementType;
import com.ibm.selmate.jaxb.stubs.NavigationHistoryCommandType;
import com.ibm.selmate.jaxb.stubs.NavigationType;
import com.ibm.selmate.jaxb.stubs.OpenURLCommandType;
import com.ibm.selmate.jaxb.stubs.OptionSelectionType;
import com.ibm.selmate.jaxb.stubs.PrintCommandType;
import com.ibm.selmate.jaxb.stubs.ReadStateCommandType;
import com.ibm.selmate.jaxb.stubs.ReadValueCommandType;
import com.ibm.selmate.jaxb.stubs.RefreshCommandType;
import com.ibm.selmate.jaxb.stubs.ScreenshotCommandType;
import com.ibm.selmate.jaxb.stubs.Script;
import com.ibm.selmate.jaxb.stubs.SelectCommandType;
import com.ibm.selmate.jaxb.stubs.SelectOptionsCommandType;
import com.ibm.selmate.jaxb.stubs.SelectOptionsCommandType.Options;
import com.ibm.selmate.jaxb.stubs.SelectOptionsCommandType.Options.Option;
import com.ibm.selmate.jaxb.stubs.SelectionStatusType;
import com.ibm.selmate.jaxb.stubs.SwitchToDefaultContentCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToFrameCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToParentWindowCommandType;
import com.ibm.selmate.jaxb.stubs.SwitchToWindowCommandType;
import com.ibm.selmate.jaxb.stubs.TimeUnitType;
import com.ibm.selmate.jaxb.stubs.WaitCommandType;
import com.ibm.selmate.jaxb.stubs.WaitCommandType.Duration;
import com.ibm.selmate.jaxb.stubs.WritePasswordCommandType;
import com.ibm.selmate.jaxb.stubs.WriteTextCommandType;

public class SelmateScriptGeneratorImpl implements SelmateScriptGenerator {
	static final Logger logger = Logger.getLogger("reportsLogger");

	public String generate(List<ScriptCommand> scriptCommands) throws SelmateXLSAdapterException {

		try {

			logger.info("Start of generate method inside SelmateScriptGeneratorImpl");

			Script script = new Script();

			String name = null;
			String inputValue = null;
			List<AbstractCommandType> commands = script.getCommand();

			// read each row from excel and set it to scriptCommand
			for (ScriptCommand scriptCommand : scriptCommands) {
				name = scriptCommand.getName();

				if (null != name && !name.equalsIgnoreCase("")) {

					if (name.equalsIgnoreCase(SelmateConstants.OPEN_COMMAND)) {
						OpenURLCommandType openURLCommandType = new OpenURLCommandType();
						openURLCommandType.setUrl(scriptCommand.getInputValue());
						openURLCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(openURLCommandType);
					} else if (name.equals(SelmateConstants.CLOSE_COMMAND)) {
						CloseCommandType closeCommandType = new CloseCommandType();
						closeCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(closeCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.REFRESH_COMMAND)) {
						RefreshCommandType refreshCommandType = new RefreshCommandType();
						refreshCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(refreshCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.SWITCH_WINDOW_COMMAND)) {
						SwitchToWindowCommandType switchToWindowCommandType = new SwitchToWindowCommandType();
						switchToWindowCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(switchToWindowCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.SWITCH_PARENT_WINDOW_COMMAND)) {
						SwitchToParentWindowCommandType switchToParentWindowCommandType = new SwitchToParentWindowCommandType();
						switchToParentWindowCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(switchToParentWindowCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.SWITCH_DEFULT_CONTENT_COMMAND)) {
						SwitchToDefaultContentCommandType switchToDefaultContentCommandType = new SwitchToDefaultContentCommandType();
						switchToDefaultContentCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(switchToDefaultContentCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.SWITCH_FRAME_COMMAND)) {
						SwitchToFrameCommandType switchToFrameCommandType = new SwitchToFrameCommandType();
						if ("ID".equals(scriptCommand.getLocatorType())) {
							switchToFrameCommandType.setId(scriptCommand.getLocatorValue());
						} else if ("INDEX".equals(scriptCommand.getLocatorType())) {
							switchToFrameCommandType.setIndex(Integer.parseInt(scriptCommand.getLocatorValue()));
						}
						switchToFrameCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(switchToFrameCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.ACCEPT_ALERT_COMMAND)) {
						commands.add(new AcceptAlertCommandType());
					} else if (name.equalsIgnoreCase(SelmateConstants.DISMISS_ALERT_COMMAND)) {
						commands.add(new DismissAlertCommandType());
					} else if (name.equalsIgnoreCase(SelmateConstants.AUTHENTICATE_COMMAND)) {
						AuthenticateAlertCommandType authenticateAlertCommandType = new AuthenticateAlertCommandType();
						authenticateAlertCommandType.setUser(scriptCommand.getInputValue(0));
						authenticateAlertCommandType.setPwd(scriptCommand.getInputValue(1));
						commands.add(authenticateAlertCommandType);
					} else if ((name.equalsIgnoreCase(SelmateConstants.HIST_FWD_COMMAND))
							|| (name.equalsIgnoreCase(SelmateConstants.HIST_BKWD_COMMAND))) {

						NavigationHistoryCommandType navigationHistoryCommandType = new NavigationHistoryCommandType();

						if (name.equalsIgnoreCase(SelmateConstants.HIST_FWD_COMMAND)) {
							navigationHistoryCommandType.setType(NavigationType.FORWARD);
						} else if (name.equalsIgnoreCase(SelmateConstants.HIST_BKWD_COMMAND)) {
							navigationHistoryCommandType.setType(NavigationType.BACKWARD);
						}
						navigationHistoryCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(navigationHistoryCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.ASSIGN_COMMAND)) {

						AssignCommandType assignCommandType = new AssignCommandType();
						assignCommandType.setVariable(scriptCommand.getVariableName());
						assignCommandType.setValue(scriptCommand.getInputValue());
						assignCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(assignCommandType);

					} else if (name.equalsIgnoreCase(SelmateConstants.WRITE_COMMAND)) {

						WriteTextCommandType writeTextCommandType = new WriteTextCommandType();
						ElementType elementType = new ElementType();
						elementType.setLocator(getElementLocator(scriptCommand));
						writeTextCommandType.setElement(elementType);
						writeTextCommandType.setValue(scriptCommand.getInputValue());
						writeTextCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(writeTextCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.WRITE_PWD_COMMAND)) {
						WritePasswordCommandType writePasswordCommandType = new WritePasswordCommandType();
						ElementType elementType = new ElementType();
						elementType.setLocator(getElementLocator(scriptCommand));
						writePasswordCommandType.setElement(elementType);
						writePasswordCommandType.setValue(scriptCommand.getInputValue());
						writePasswordCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(writePasswordCommandType);
					} else if ((name.equalsIgnoreCase(SelmateConstants.SELECT_STATUS))
							|| (name.equalsIgnoreCase(SelmateConstants.DESELECT_STATUS))
							|| name.equalsIgnoreCase(SelmateConstants.SELECT_OPTION_COMMAND)
							|| name.equalsIgnoreCase(SelmateConstants.DESELECT_OPTION_COMMAND)) {
						SelectCommandType selectCommandType = new SelectCommandType();
						SelectOptionsCommandType selectOptionsCommandType = new SelectOptionsCommandType();
						ElementType elementType = new ElementType();
						elementType.setLocator(getElementLocator(scriptCommand));
						if ((name.equalsIgnoreCase(SelmateConstants.SELECT_STATUS))
								|| (name.equalsIgnoreCase(SelmateConstants.DESELECT_STATUS))) {
							selectCommandType.setElement(elementType);
							selectCommandType.setStatus(getSelectionStatus(name));
							selectCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(selectCommandType);
						} else if ((name.equalsIgnoreCase(SelmateConstants.SELECT_OPTION_COMMAND))
								|| (name.equalsIgnoreCase(SelmateConstants.DESELECT_OPTION_COMMAND))) {
							selectOptionsCommandType.setElement(elementType);
							selectOptionsCommandType.setStatus(getSelectionStatus(name));
							selectOptionsCommandType.setOptions(getOptions(scriptCommand));
							selectOptionsCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(selectOptionsCommandType);
						}
					} else if (name.equalsIgnoreCase(SelmateConstants.ACTIVATE_COMMAND)
							|| name.equalsIgnoreCase(SelmateConstants.CLICK_COMMAND)
							|| name.equalsIgnoreCase(SelmateConstants.DRAG_FROM_COMMAND)
							|| name.equalsIgnoreCase(SelmateConstants.DROP_TO_COMMAND)) {

						ElementLocatorType linkElementLocatorType = new ElementLocatorType();
						if (null != scriptCommand.getLocatorType()
								&& scriptCommand.getLocatorType().equalsIgnoreCase(SelmateConstants.ID_LOCATOR_TYPE)) {
							linkElementLocatorType.setId(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.XPATH_LOCATOR_TYPE)) {
							linkElementLocatorType.setXpath(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.CLASSNAME_LOCATOR_TYPE)) {
							linkElementLocatorType.setClassName(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.TAGNAME_LOCATOR_TYPE)) {
							linkElementLocatorType.setTagName(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.CSS_SELECTOR_LOCATOR_TYPE)) {
							linkElementLocatorType.setCssSelector(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.LINKTEXT_LOCATOR_TYPE)) {
							linkElementLocatorType.setLinkText(scriptCommand.getLocatorValue());
						} else if (null != scriptCommand.getLocatorType() && scriptCommand.getLocatorType()
								.equalsIgnoreCase(SelmateConstants.PARTLINKTEXT_LOCATOR_TYPE)) {
							linkElementLocatorType.setPartialLinkText(scriptCommand.getLocatorValue());
						}

						ElementType elementType = new ElementType();
						elementType.setLocator(linkElementLocatorType);

						if (name.equalsIgnoreCase(SelmateConstants.ACTIVATE_COMMAND)) {

							ActivateCommandType activateCommandType = new ActivateCommandType();
							activateCommandType.setElement(elementType);
							activateCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(activateCommandType);

						} else if (name.equalsIgnoreCase(SelmateConstants.CLICK_COMMAND)) {

							ClickCommandType clickCommandType = new ClickCommandType();
							clickCommandType.setElement(elementType);
							clickCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(clickCommandType);

						} else if (name.equalsIgnoreCase(SelmateConstants.DRAG_FROM_COMMAND)) {

							DragCommandType dragCommandType = new DragCommandType();
							dragCommandType.setElement(elementType);
							dragCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(dragCommandType);

						} else if (name.equalsIgnoreCase(SelmateConstants.DROP_TO_COMMAND)) {

							DropCommandType dropCommandType = new DropCommandType();
							dropCommandType.setElement(elementType);
							dropCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(dropCommandType);

						}

					} else if ((name.equalsIgnoreCase(SelmateConstants.READSTATE_COMMAND))
							|| (name.equalsIgnoreCase(SelmateConstants.READVALUE_COMMAND))) {
						ElementType elementType = new ElementType();
						elementType.setLocator(getElementLocator(scriptCommand));

						if (name.equalsIgnoreCase(SelmateConstants.READSTATE_COMMAND)) {
							ReadStateCommandType readStateCommandType = new ReadStateCommandType();
							readStateCommandType.setElement(elementType);
							readStateCommandType.setState(scriptCommand.getInputValue());
							readStateCommandType.setVariable(scriptCommand.getVariableName());
							readStateCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(readStateCommandType);
						} else if (name.equalsIgnoreCase(SelmateConstants.READVALUE_COMMAND)) {
							ReadValueCommandType readAttributeCommandType = new ReadValueCommandType();
							readAttributeCommandType.setElement(elementType);
							readAttributeCommandType.setVariable(scriptCommand.getVariableName());
							readAttributeCommandType.setAttributeName(scriptCommand.getInputValue());
							readAttributeCommandType.setStepDescription(scriptCommand.getNarration());
							commands.add(readAttributeCommandType);
						}

					} else if ((name.equalsIgnoreCase(SelmateConstants.WAIT_COMMAND))
							|| (name.equalsIgnoreCase(SelmateConstants.DELAY_COMMAND))) {

						WaitCommandType waitCommandType = new WaitCommandType();
						Duration duration = new Duration();

						if ((inputValue = scriptCommand.getInputValue()) != null && !inputValue.trim().equals("")) {
							duration.setValue((long) Double.parseDouble(inputValue));
						}
						duration.setUnit(TimeUnitType.S);
						waitCommandType.setDuration(duration);

						if (name.equalsIgnoreCase(SelmateConstants.WAIT_COMMAND)) {
							waitCommandType.setType(DelayType.IMPLICIT);
						} else {
							waitCommandType.setType(DelayType.INTERMEDIATE);
						}
						waitCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(waitCommandType);

					} else if ((name.equalsIgnoreCase(SelmateConstants.ASSERT_EQ_COMMAND))
							|| (name.equalsIgnoreCase(SelmateConstants.ASSERT_NEQ_COMMAND))
							|| (name.equalsIgnoreCase(SelmateConstants.ASSERT_EQ_WARN_COMMAND)
									|| name.equalsIgnoreCase(SelmateConstants.ASSERT_NEQ_WARN_COMMAND))) {

						AssertCommandType assertCommandType = new AssertCommandType();
						assertCommandType.setActualValue(scriptCommand.getInputValue(0));
						assertCommandType.setExpectedValue(scriptCommand.getInputValue(1));

						assertCommandType.setFailureMsg(scriptCommand.getInputValue(2));

						if (name.equalsIgnoreCase(SelmateConstants.ASSERT_EQ_COMMAND)) {
							assertCommandType.setType(AssertType.ERROR);
							assertCommandType.setComparisonType(com.ibm.selmate.jaxb.stubs.ComparisonType.EQUAL);
						} else if (name.equalsIgnoreCase(SelmateConstants.ASSERT_NEQ_COMMAND)) {
							assertCommandType.setType(AssertType.ERROR);
							assertCommandType.setComparisonType(com.ibm.selmate.jaxb.stubs.ComparisonType.NOT_EQUAL);
						} else if (name.equalsIgnoreCase(SelmateConstants.ASSERT_EQ_WARN_COMMAND)) {
							assertCommandType.setComparisonType(com.ibm.selmate.jaxb.stubs.ComparisonType.EQUAL);
							assertCommandType.setType(AssertType.WARN);
						} else if (name.equalsIgnoreCase(SelmateConstants.ASSERT_NEQ_WARN_COMMAND)) {
							assertCommandType.setComparisonType(com.ibm.selmate.jaxb.stubs.ComparisonType.NOT_EQUAL);
							assertCommandType.setType(AssertType.WARN);
						}
						assertCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(assertCommandType);

					} else if (name.equalsIgnoreCase(SelmateConstants.CUSTOM_ACTION_COMMAND)) {

						CustomCommandType customCommandType = new CustomCommandType();
						customCommandType.setCommandClass(scriptCommand.getInputValue());
						Iterator<String> itr = scriptCommand.iterateInputValues();
						if (itr.hasNext()) {
							customCommandType.setCommandClass(itr.next());
						}

						CustomCommandType.Arguments arguments = new CustomCommandType.Arguments();
						while (itr.hasNext()) {
							arguments.getArgument().add(itr.next());
						}
						customCommandType.setArguments(arguments);
						customCommandType.setStepDescription(scriptCommand.getNarration());
						commands.add(customCommandType);
					} else if (name.equalsIgnoreCase(SelmateConstants.SCREENSHOT_COMMAND)) {
						ScreenshotCommandType screenshotCommand = new ScreenshotCommandType();
						screenshotCommand.setStepDescription(scriptCommand.getNarration());
						commands.add(screenshotCommand);
					} else if (name.equalsIgnoreCase(SelmateConstants.PRINT_COMMAND)) {
						PrintCommandType printCommandType = new PrintCommandType();
						printCommandType.setStepDescription(scriptCommand.getNarration());
						printCommandType.setValue(scriptCommand.getInputValue());
						commands.add(printCommandType);
					}

				}
			}

			logger.info("List of commands created successfully :" + commands);

			JAXBContext jaxbContext = JAXBContext.newInstance(Script.class, ActivateCommandType.class,
					AcceptAlertCommandType.class, DismissAlertCommandType.class, AuthenticateAlertCommandType.class,
					AssertCommandType.class, AssignCommandType.class, ClickCommandType.class, CustomCommandType.class,
					DragCommandType.class, DropCommandType.class, NavigationHistoryCommandType.class,
					OpenURLCommandType.class, ReadValueCommandType.class, ReadStateCommandType.class,
					RefreshCommandType.class, SelectCommandType.class, SelectOptionsCommandType.class,
					SwitchToDefaultContentCommandType.class, SwitchToWindowCommandType.class,
					SwitchToFrameCommandType.class, WaitCommandType.class, WriteTextCommandType.class,
					WritePasswordCommandType.class, ScreenshotCommandType.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();

			jaxbMarshaller.marshal(script, writer);

			String selmateScript = writer.getBuffer().toString();

			logger.info("End of generate method inside SelmateScriptGeneratorImpl");

			return selmateScript.toString();
		} catch (Exception ex) {
			throw new SelmateXLSAdapterException(ex);
		}

	}

	private Options getOptions(ScriptCommand scriptCommand) {
		Options options = new Options();
		Iterator<String> itrValues = scriptCommand.iterateInputValues();
		List<Option> optionList = new ArrayList<SelectOptionsCommandType.Options.Option>();
		String optionValue = null;
		while (itrValues.hasNext()) {
			optionValue = itrValues.next();
			if (optionValue != null && !optionValue.trim().equals("")) {
				Option option = new Option();
				option.setValue(optionValue);
				option.setBy(OptionSelectionType.VISIBLE_TEXT);
				optionList.add(option);
			}
		}
		options.getOption().addAll(optionList);
		return options;
	}

	private SelectionStatusType getSelectionStatus(String name) {
		if ((name.equalsIgnoreCase(SelmateConstants.SELECT_STATUS))
				|| (name.equalsIgnoreCase(SelmateConstants.SELECT_OPTION_COMMAND))) {
			return SelectionStatusType.SELECT;
		} else if ((name.equalsIgnoreCase(SelmateConstants.DESELECT_STATUS))
				|| (name.equalsIgnoreCase(SelmateConstants.DESELECT_OPTION_COMMAND))) {
			return SelectionStatusType.DESELECT;
		} else {
			return null;
		}
	}

	private ElementLocatorType getElementLocator(ScriptCommand scriptCommand) {
		ElementLocatorType elementLocatorType = new ElementLocatorType();
		String locatorType = null;
		if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.ID_LOCATOR_TYPE)) {
			elementLocatorType.setId(scriptCommand.getLocatorValue());
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.XPATH_LOCATOR_TYPE)) {
			elementLocatorType.setXpath(scriptCommand.getLocatorValue());
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.CLASSNAME_LOCATOR_TYPE)) {
			elementLocatorType.setClassName(scriptCommand.getLocatorValue());
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.TAGNAME_LOCATOR_TYPE)) {
			elementLocatorType.setTagName(scriptCommand.getLocatorValue());
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.CSS_SELECTOR_LOCATOR_TYPE)) {
			elementLocatorType.setCssSelector(scriptCommand.getLocatorValue());
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.PARTLINKTEXT_LOCATOR_TYPE)) {
			elementLocatorType.setPartialLinkText(locatorType);
		} else if (null != (locatorType = scriptCommand.getLocatorType())
				&& locatorType.equalsIgnoreCase(SelmateConstants.LINKTEXT_LOCATOR_TYPE)) {
			elementLocatorType.setLinkText(locatorType);
		}
		return elementLocatorType;
	}

}
