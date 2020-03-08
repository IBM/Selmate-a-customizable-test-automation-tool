
package com.ibm.selmate.builder;

import org.apache.log4j.Logger;

import com.ibm.selmate.command.Element;
import com.ibm.selmate.command.Locator;
import com.ibm.selmate.jaxb.stubs.AbstractElementInteractionCommandType;
import com.ibm.selmate.jaxb.stubs.AbstractSelectCommandType;
import com.ibm.selmate.jaxb.stubs.ElementLocatorType;
import com.ibm.selmate.jaxb.stubs.ElementType;
import com.ibm.selmate.jaxb.stubs.SelectionStatusType;

public abstract class AbstractElementInteractionCommandBuilder extends AbstractSelmateCommandBuilder {

	private Logger logger = Logger.getLogger(AbstractElementInteractionCommandBuilder.class);

	protected Element buildElement(AbstractElementInteractionCommandType elementInteractionCommandType) {
		logger.info("START");

		ElementType elementType = elementInteractionCommandType.getElement();
		ElementLocatorType locatorType = elementType.getLocator();
		Element element = new Element();
		Locator locator = new Locator();
		buildLocator((ElementLocatorType) locatorType, locator);
		element.setLocator(locator);
		logger.info("END");

		return element;
	}

	protected void buildLocator(ElementLocatorType locatorType, Locator locator) {
		logger.info("START");
		String value = null;
		if (null != (value = locatorType.getId())) {
			locator.setType(Locator.Type.ID);
			locator.setValue(value);
		} else if (null != (value = locatorType.getXpath())) {
			locator.setType(Locator.Type.XPATH);
			locator.setValue(value);
		} else if (null != (value = locatorType.getClassName())) {
			locator.setType(Locator.Type.CLASS_NAME);
			locator.setValue(value);
		} else if (null != (value = locatorType.getTagName())) {
			locator.setType(Locator.Type.TAG_NAME);
			locator.setValue(value);
		} else if (null != (value = locatorType.getCssSelector())) {
			locator.setType(Locator.Type.CSS_SELECTOR);
			locator.setValue(value);
		} else if (null != locatorType.getLinkText()) {
			locator.setType(Locator.Type.LINKTEXT);
			locator.setValue(locatorType.getLinkText());
		} else if (null != locatorType.getPartialLinkText()) {
			locator.setType(Locator.Type.PARTIAL_LINKTEXT);
			locator.setValue(locatorType.getPartialLinkText());
		}
		logger.info("END");
	}

	protected boolean getStatus(AbstractSelectCommandType selectCommandType) {
		logger.info("START");
		SelectionStatusType status = selectCommandType.getStatus();
		if (status == SelectionStatusType.SELECT) {
			return true;
		} else {
			return false;
		}
	}

}
